package com.wulianedu.netty.server;

import com.wulianedu.netty.listener.MessageListener;
import com.wulianedu.netty.listener.ServerSendMsgCallback;
import com.wulianedu.netty.server.socket.ServerSocketBootstrap;

/**
 * 1. 监听多个 port 端口；
 * 2. 根据客户端标识，接受client并且发送数据，需要自己失败处理数据；
 * 3. 接受数据，通过监听 listener 收取数据；
 * 4. 关闭已经失效的连接；
 * Created by connli on 2017/10/31.
 */
public class ServerInstance {

    private ServerSocketBootstrap serverBootstrap;

    public ServerInstance(MessageListener messageListener, ServerSendMsgCallback failMsg, int port) {
        serverBootstrap = new ServerSocketBootstrap(messageListener, failMsg, port);
    }

    public void connect() {
        serverBootstrap.init();
    }

    public void sendMessage(String identify, String msg) {
        sendMessage(identify, msg, -1);
    }

    public void sendMessage(String identify, String msg, int code) {
        sendMessage(identify, msg, code, null);
    }

    public void sendMessage(String identify, String msg, int code, Object ext) {
        serverBootstrap.sendMessage(identify, msg, code, ext);
    }

    public boolean channelStatus(String identify) {
        boolean rst = serverBootstrap.channelStatus(identify);
        return rst;
    }


}
