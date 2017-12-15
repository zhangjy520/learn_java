title: jvm调试- tomcat假死-优化tomcat配置
author: zjy
tags: []
categories: []
date: 2017-09-15 16:34:00
---
## 查看某id相关的子线程
	ps -Lf 29295|wc -l
   
## 查看 jvm线程情况
	jstack pid 
    jstack pid >1.txt (输出到文件)
    
通过以上命令，发现，大多数线程处于  time_wating状态的线程占了 90%  .分析发现是程序代码有问题，对异常链接没有显示释放关闭等操作，才导致一旦出错，这个线程就一直不释放，线程数量一直增加。  
    
## 查看tcp ip 线程链接情况
netstat -np | grep tcp
统计
netstat -n | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}' 

## 删除docker某个镜像的相关容器
	sudo docker ps -a | awk '{ print $1,$2 }' | grep  这里是镜像的名称/或者id| awk '{print $1 }' | xargs -I {} sudo docker rm {}
    


## 问题
		线上tomcat总是假死：所谓假死，就是tomcat进程并没有关闭，并且也没有异常日志，一切看似正常，但是tomcat就是访问不到，不是找不到地址，而是响应超时超时超时!
        通过 
        ps -ef|grep tomcat
		ps -Lf pid|wc -l 
		查看到tomcat的当前正在访问的子线程数量大于 server.xml里配置的最大线程数量。这才导致了项目访问不到，因为前面有线程一直在等待，并没有关闭，tomcat不会为新来的链接分配资源，导致新来的客户端访问得不到响应，才是超时。试想如果tomcat真的挂了，也不会是超时，而是地址错误等提示了。

### 分析一下 server.xml（以下是修改后的server.xml）

	<?xml version='1.0' encoding='utf-8'?> 
    <Server port="8003" shutdown="SHUTDOWN">
      <Listener className="org.apache.catalina.startup.VersionLoggerListener" /> 
      <Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="on" /> 
      <Listener className="org.apache.catalina.core.JasperListener" /> 
      <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" />
      <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener" />
      <Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener" />

      <GlobalNamingResources> 
        <Resource name="UserDatabase" auth="Container"
                  type="org.apache.catalina.UserDatabase"
                  description="User database that can be updated and saved"
                  factory="org.apache.catalina.users.MemoryUserDatabaseFactory"
                  pathname="conf/tomcat-users.xml" />
      </GlobalNamingResources> 
      <Service name="Catalina"> 
      
      // 这个节点是打开tomcat线程池，接下来的connector里面应用它
          <Executor name="tomcatThreadPool" namePrefix="catalina-exec-"  
          maxThreads="1000" minSpareThreads="50" maxIdleTime="600000"/> 

	//这里主要除了应用executor，还得注意一下protocol
        <Connector port="10003" connectionTimeout="20000"
                   redirectPort="8443" maxThreads="3000" acceptCount="3000" protocol="org.apache.coyote.http11.Http11NioProtocol" executor="tomcatThreadPool" />

        <Connector port="8030" protocol="AJP/1.3" redirectPort="8443" />


        <Engine name="Catalina" defaultHost="localhost">


          <Realm className="org.apache.catalina.realm.LockOutRealm">

            <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
                   resourceName="UserDatabase"/>
          </Realm>

          <Host name="localhost"  appBase="webapps"
                unpackWARs="true" autoDeploy="true">

            <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
                   prefix="localhost_access_log." suffix=".txt"
                   pattern="%h %l %u %t &quot;%r&quot; %s %b" />

          </Host>
        </Engine>
      </Service>
    </Server>
    
    
 ### 对tomcat简单分析
 
### tomcat线程配置

1 打开/conf/server.xml文件，在Connector之前配置一个线程池：
 
	<Executor name="tomcatThreadPool"   
        namePrefix="tomcatThreadPool-"   
        maxThreads="1000"   
        maxIdleTime="300000"  
        minSpareThreads="200"/>  
        
        
    重要参数说明：
        name：共享线程池的名字。这是Connector为了共享线程池要引用的名字，该名字必须唯一。默认值：None；

        namePrefix:在JVM上，每个运行线程都可以有一个name 字符串。这一属性为线程池中每个线程的name字符串设置了一个前缀，Tomcat将把线程号追加到这一前缀的后面。默认值：tomcat-exec-；

        maxThreads：该线程池可以容纳的最大线程数。默认值：200；

        maxIdleTime：在Tomcat关闭一个空闲线程之前，允许空闲线程持续的时间(以毫秒为单位)。只有当前活跃的线程数大于minSpareThread的值，才会关闭空闲线程。默认值：60000(一分钟)。

        minSpareThreads：Tomcat应该始终打开的最小不活跃线程数。默认值：25。


2：配置Connector

    <Connector executor="tomcatThreadPool"  
               port="8080" protocol="HTTP/1.1"  
                   connectionTimeout="20000"  
                   redirectPort="8443"   
               minProcessors="5"  
               maxProcessors="75"  
               acceptCount="1000"/>  


	重要参数说明：
          executor：表示使用该参数值对应的线程池；

          minProcessors：服务器启动时创建的处理请求的线程数；

          maxProcessors：最大可以创建的处理请求的线程数；

          acceptCount：指定当所有可以使用的处理请求的线程数都被使用时，可以放到处理队列中的请求数，超过这个数的请求将不予处理。
          

 #### Tomcat Connector(连接器)有三种运行模式：bio   nio    apr
 
 
 1 、bio(blocking I/O)

		 配置方式：protocol="HTTP/1.1"
         
		即阻塞式I/O操作，表示Tomcat使用的是传统的Java I/O操作(即java.io包及其子包)。是基于JAVA的HTTP/1.1连接器，Tomcat7以下版本在默认情况下是以bio模式运行的。一般而言，bio模式是三种运行模式中性能最低的一种。我们可以通过Tomcat Manager来查看服务器的当前状态。（Tomcat7 或以下，在 Linux 系统中默认使用这种方式）

		一个线程处理一个请求，缺点：并发量高时，线程数较多，浪费资源
        
        
2、nio(new I/O)

		配置方式：protocol="org.apache.coyote.http11.Http11NioProtocol"
        是Java SE 1.4及后续版本提供的一种新的I/O操作方式(即java.nio包及其子包)。Java nio是一个基于缓冲区、并能提供非阻塞I/O操作的Java API，因此nio也被看成是non-blocking I/O的缩写。它拥有比传统I/O操作(bio)更好的并发运行性能。要让Tomcat以nio模式来运行只需要在Tomcat安装目录/conf/server.xml 中将对应的中protocol的属性值改为 org.apache.coyote.http11.Http11NioProtocol即可
		利用 Java 的异步请求 IO 处理，可以通过少量的线程处理大量的请求
		注意： Tomcat8 以上版本在 Linux 系统中，默认使用的就是NIO模式，不需要额外修改 ，Tomcat7必须修改Connector配置来启动
    
    
3、apr(Apache Portable Runtime/Apache可移植运行时) （ 安装配置过程相对复杂）

		Tomcat将以JNI的形式调用Apache HTTP服务器的核心动态链接库来处理文件读取或网络传输操作，从而大大地提高Tomcat对静态文件的处理性能。Tomcat apr也是在Tomcat上运行高并发应用的首选模式。从操作系统级别来解决异步的IO问题

		APR是使用原生C语言编写的非堵塞I/O，利用了操作系统的网络连接功能，速度很快。 
		但是需先安装apr和native，若直接启动就支持apr，能大幅度提升性能，不亚于魔兽开局爆高科技兵种，威力强大
		Tomcat apr的配置需要以下三个组件的支持：

   	1 APR library
    
   	2 JNI wrappers for APR used by Tomcat(libtcnative)[简单地说，就是自带的tomcat-native]

   	3 OpenSSL libraries 
    
		与配置nio运行模式一样，也需要将对应Connector的protocol属性值改为 org.apache.coyote.http11.Http11AprProtocol

		server.xml 文件中的配置

            <Connector port="8080" protocol="org.apache.coyote.http11.Http11AprProtocol"
            URIEncoding="UTF-8"
            maxConnections="10000"
            maxThreads="2000"
            acceptCount="2000"
            minSpareThreads="100"
            compression="on"
            compressionMinSize="2048"
            compressableMimeType="text/html,text/xml,text/javascript,text/css,text/plain"
            enableLookups="false"
            disableUploadTimeout="true"
            connectionTimeout="20000"
            redirectPort="8443" />

    
    
   3.1
    	配置之前首先安装APR以及与 Tomcat-native 整合（之前先安装依赖）
      APR及APR依赖的下载

		APR下载：http://apr.apache.org/download.cgi
        

        
①安装apr

      1 在目录/opt/apr/apr-1.5.2 下执行
       ./configure --prefix=/usr/local/apr

      2 make

      3 make install
② 安装 apr-iconv

     1  /opt/apr/apr-iconv-1.2.1
       ./configure --prefix=/usr/local/apr-iconv --with-apr=/usr/local/apr

    2 make

    3 make install
③ 安装apr-util

    1 ./configure --prefix=/usr/local/apr-util --with-apr=/usr/local/apr --with-apr-iconv=/usr/local/apr-iconv/bin/apriconv

    2 make

    3 make install
    
 ④安装tomcat-native 
 
 	不需要下载，tomcat自带 在bin下面 tomcat-native.tar.gz这个文件
 	
    1 tar -zxvf tomcat-native.tar.gz
    2 cd tomcat-native-1.1.33-src/jni/native
    3 ./configure --with-apr = /usr/local/apr
    4 make
    5 make install
    
    
 ⑤ 配置APR的环境变量

    vim /opt/tomcat7/bin/catalina.sh

    编辑添加如下内容

    以下内容粘在开头（表示一启动就执行）

    LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/apr/lib export LD_LIBRARY_PATH