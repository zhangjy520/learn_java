package com.wulianedu.netty.listener;

/**
 * Created by connli on 2018/3/13.
 */
public interface ServerSendMsgCallback {

    void failureMessage(String identity, Object message, int code);

}
