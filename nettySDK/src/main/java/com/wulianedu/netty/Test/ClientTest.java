package com.wulianedu.netty.Test;

import com.wulianedu.netty.client.ClientInstance;
import com.wulianedu.netty.listener.ClientSendMsgCallback;
import com.wulianedu.netty.listener.MessageListener;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by connli on 2017/11/3.
 */
public class ClientTest implements MessageListener, ClientSendMsgCallback {

    static ClientInstance instance;
    public static void main(String[] args) {
        new ClientTest().start();

        int n = 0;
        while (true) {
            Scanner sc = new Scanner(System.in);

            String val = sc.nextLine();

            String msg = "{\"opt\":\"haha\",\"data\":\""+val+"\"}";

            instance.sendMessage(msg);

             System.out.println(val);
        }
    }

    private void start() {
        // 启动初始化
        instance = new ClientInstance(this, this, "channel_identify_flag", "127.0.0.1", 20001);
        instance.connect();

    }

    // 接受消息
    @Override
    public void onMessageReceive(String msg) {
        System.out.println("client message recv:" + msg);
    }

    /**
     * 失败消息回调
     * message[消息内容] code[标识发送消息]
     * @param message
     * @param code
     */
    @Override
    public void failureMessage(Object message, int code) {

    }
}
