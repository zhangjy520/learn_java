package com.wulianedu.netty.listener;

/**
 * Created by connli on 2018/3/13.
 */
public interface ClientSendMsgCallback {

    void failureMessage(Object object, int code);

}
