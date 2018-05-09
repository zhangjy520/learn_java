title: active_mq 导致的一些阻塞问题
author: zjy
date: 2017-09-29 14:48:02
tags:
---
## 情景
	通过 jstack pid >1.txt  发现tomcat有很多BLOCKED的线程， 具体如下截取的一部分。
    
      "pool-2174-thread-1" #2397 prio=5 os_prio=0 tid=0x00007f3ca21e9800 nid=0xa00 waiting for monitor entry [0x00007f3bcb2b7000]
     java.lang.Thread.State: BLOCKED (on object monitor)
      at org.apache.activemq.pool.PooledConnectionFactory.createConnection(PooledConnectionFactory.java:200)
	- waiting to lock <0x00000006c80a0e10> (a org.apache.activemq.pool.PooledConnectionFactory)
	at org.apache.activemq.pool.PooledConnectionFactory.createConnection(PooledConnectionFactory.java:195)
	at org.springframework.jms.support.JmsAccessor.createConnection(JmsAccessor.java:180)
	at org.springframework.jms.core.JmsTemplate.execute(JmsTemplate.java:484)
	at org.springframework.jms.core.JmsTemplate.send(JmsTemplate.java:569)
	at org.springframework.jms.core.JmsTemplate.convertAndSend(JmsTemplate.java:658)
	at org.springframework.jms.core.JmsTemplate.convertAndSend(JmsTemplate.java:649)
	at cc.gukeer.common.interceptor.DataPushInterceptor$1.run(DataPushInterceptor.java:115)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:748)
	
    由于有太多处于阻塞状态的线程，导致tomcat线程池一直处于满载的状态，tomcat假死，服务无法正常访问。
    
    
## 排查
	通过以上代码发现，造成阻塞的线程基本全是 jms获取链接出了问题，我们先检查一下active_mq的状态，果然，通过mq的后台虽然能看到队列信息等，但是能明显感觉到后台也是比较卡顿的，通过分析查看active_mq的日志，我们得到如下一段异常代码：
    	DefaultMessageListenerContainer-1
    2017-09-29 13:45:00,859 | INFO  | Transport failed: org.apache.activemq.transport.TransportDisposedIOException: Peer (vm://localhost#611259) disposed. | org.apache.activemq.broker.TransportConnection.Transport | DefaultMessageListenerContainer-1
    org.apache.activemq.transport.TransportDisposedIOException: Peer (vm://localhost#611259) disposed.
        at org.apache.activemq.transport.vm.VMTransport.stop(VMTransport.java:159)
        at org.apache.activemq.transport.vm.VMTransportServer$1.stop(VMTransportServer.java:81)
        at org.apache.activemq.transport.TransportFilter.stop(TransportFilter.java:65)
        at org.apache.activemq.transport.TransportFilter.stop(TransportFilter.java:65)
        at org.apache.activemq.transport.ResponseCorrelator.stop(ResponseCorrelator.java:132)
        at org.apache.activemq.util.ServiceSupport.dispose(ServiceSupport.java:43)
        at org.apache.activemq.ActiveMQConnection.close(ActiveMQConnection.java:656)
        at org.springframework.jms.connection.ConnectionFactoryUtils.releaseConnection(ConnectionFactoryUtils.java:80)
        at org.springframework.jms.listener.AbstractJmsListeningContainer.refreshSharedConnection(AbstractJmsListeningContainer.java:385)
        at org.springframework.jms.listener.DefaultMessageListenerContainer.refreshConnectionUntilSuccessful(DefaultMessageListenerContainer.java:856)
        at org.springframework.jms.listener.DefaultMessageListenerContainer.recoverAfterListenerSetupFailure(DefaultMessageListenerContainer.java:838)
        at org.springframework.jms.listener.DefaultMessageListenerContainer$AsyncMessageListenerInvoker.run(DefaultMessageListenerContainer.java:969)
        at java.lang.Thread.run(Thread.java:745)
    2017-09-29 13:45:01,437 | WARN  | Failed to add Connection | org.apache.activemq.broker.TransportConnection | VMTransport: vm://localhost#611261
    java.lang.SecurityException: User name or password is invalid.
        at org.apache.activemq.security.SimpleAuthenticationBroker.addConnection(SimpleAuthenticationBroker.java:80)
        at org.apache.activemq.broker.MutableBrokerFilter.addConnection(MutableBrokerFilter.java:91)
        at org.apache.activemq.broker.TransportConnection.processAddConnection(TransportConnection.java:694)
        at org.apache.activemq.command.ConnectionInfo.visit(ConnectionInfo.java:137)
        at org.apache.activemq.broker.TransportConnection.service(TransportConnection.java:309)
        at org.apache.activemq.broker.TransportConnection$1.onCommand(TransportConnection.java:185)
        at org.apache.activemq.transport.ResponseCorrelator.onCommand(ResponseCorrelator.java:116)
        at org.apache.activemq.transport.TransportFilter.onCommand(TransportFilter.java:69)
        at org.apache.activemq.transport.vm.VMTransport.iterate(VMTransport.java:218)
        at org.apache.activemq.thread.DedicatedTaskRunner.runTask(DedicatedTaskRunner.java:98)
        at org.apache.activemq.thread.DedicatedTaskRunner$1.run(DedicatedTaskRunner.java:36)
        
        
        
        其中： Transport failed: org.apache.activemq.transport.TransportDisposedIOException: Peer (vm://localhost#611259) disposed.