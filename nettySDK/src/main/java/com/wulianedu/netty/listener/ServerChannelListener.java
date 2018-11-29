package com.wulianedu.netty.listener;

import io.netty.channel.Channel;

import java.util.Map;

/**
 * Created by connli on 2017/11/3.
 */
public interface ServerChannelListener {

    void setServerChannels(Map<String, Channel> channelMap);

}
