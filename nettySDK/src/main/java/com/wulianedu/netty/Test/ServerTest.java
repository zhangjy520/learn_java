package com.wulianedu.netty.Test;

import com.wulianedu.netty.listener.MessageListener;
import com.wulianedu.netty.listener.ServerSendMsgCallback;
import com.wulianedu.netty.server.ServerInstance;

/**
 * Created by connli on 2017/11/3.
 */
public class ServerTest implements MessageListener, ServerSendMsgCallback {

    ServerInstance instance;

    public static void main(String[] args) {
       new ServerTest().start();
    }

    private void start() {
        // 启动初始化
        instance = new ServerInstance(this, this, 20001);
        instance.connect();

        // 发消息
        if (instance.channelStatus("channel_identify_flag")) {
            instance.sendMessage("channel_identify_flag", "bbb");
        }
    }

    // 接受消息
    @Override
    public void onMessageReceive(String msg) {
        System.out.println("server message recv:" + msg);
    }

    /**
     * 失败消息回调
     * identify[channel标识] message[消息内容] code[标识发送消息]
     * @param message
     * @param code
     */
    @Override
    public void failureMessage(String identity, Object message, int code) {

    }
}
