package com.wulianedu.netty.client.socket;

import com.wulianedu.netty.client.socket.heart.ClientIdleStateTrigger;
import com.wulianedu.netty.listener.MessageListener;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by connli on 2017/11/2.
 */
public class ChannelHandlerHolder {

    public static final int TIME_INTERVAL = 10 * 1000;

    public static ChannelHandler[] getClientHandlers(MessageListener messageListener,
                                       Object object,
                                       boolean ssl) {

        ChannelHandler channelHandler[] = null;

        if (ssl) {
            channelHandler = new ChannelHandler[] {
                    null,
                    new DelimiterBasedFrameDecoder(81920, Delimiters.lineDelimiter()),
                    (ChannelHandler) object,
                    new IdleStateHandler(0, 10, 0, TimeUnit.SECONDS),
                    new ClientIdleStateTrigger(),
                    new StringDecoder(CharsetUtil.UTF_8),
                    new StringEncoder(CharsetUtil.UTF_8),
                    new ClientDataHandler(messageListener)
            };
        } else {
            channelHandler = new ChannelHandler[] {
                    new DelimiterBasedFrameDecoder(81920, Delimiters.lineDelimiter()),
                    (ChannelHandler) object,
                    new IdleStateHandler(0, 10, 0, TimeUnit.SECONDS),
                    new ClientIdleStateTrigger(),
                    new StringDecoder(CharsetUtil.UTF_8),
                    new StringEncoder(CharsetUtil.UTF_8),
                    new ClientDataHandler(messageListener)
            };
        }
        return channelHandler;
    }

}
