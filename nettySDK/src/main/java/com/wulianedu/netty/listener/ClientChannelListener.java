package com.wulianedu.netty.listener;

import io.netty.channel.Channel;

/**
 * Created by connli on 2017/11/3.
 */
public interface ClientChannelListener {

    void setClientChannel(Channel channel);

}
