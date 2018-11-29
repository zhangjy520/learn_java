package com.wulianedu.netty.client;

import com.wulianedu.netty.client.socket.ClientSocketBootstrap;
import com.wulianedu.netty.listener.ClientSendMsgCallback;
import com.wulianedu.netty.listener.MessageListener;

/**
 * 1. 启动并实例化并连接多个 server + port；
 * 2. 发送数据，需要自己处理发送失败数据；
 * 3. 接受数据，通过监听 listener 收取数据；
 * 4. 重连以及处理其他错误等；
 * Created by connli on 2017/10/31.
 */
public class ClientInstance {

    private ClientSocketBootstrap bootstrap;

    public ClientInstance(MessageListener messageListener, ClientSendMsgCallback failMsg, String identify, String host, int port) {
        bootstrap = new ClientSocketBootstrap(messageListener, failMsg, identify, host, port);
    }

    public void connect() {
        bootstrap.init();
    }

    public void closeChannel() {
        bootstrap.closeChannel();
        bootstrap = null;
    }

    public void sendMessage(String msg) {
        sendMessage(msg, -1);
    }

    public void sendMessage(String msg, int code) {
        sendMessage(msg, code, null);
    }

    public void sendMessage(String msg, int code, Object ext) {
        bootstrap.sendMessage(msg, code, ext);
    }

    public boolean channelStatus() {
        boolean rst = bootstrap.channelStatus();
        return rst;
    }
}
