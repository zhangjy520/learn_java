package com.wulianedu.netty.server.socket;

import com.wulianedu.netty.listener.MessageListener;
import com.wulianedu.netty.listener.ServerChannelListener;
import com.wulianedu.netty.server.socket.heart.AcceptorIdleStateTrigger;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created by connli on 2017/2/5.
 */
public class ServerHandlerInitializer extends ChannelInitializer<SocketChannel> {

    private final ServerChannelListener channelListener;
    private final MessageListener messageListener;
    private final SslContext sslCtx;

    public ServerHandlerInitializer(ServerChannelListener channelListener,
                                    MessageListener messageListener,
                                    SslContext sslCtx) {
        this.channelListener = channelListener;
        this.messageListener = messageListener;
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        if (null != sslCtx) {
            pipeline.addLast(sslCtx.newHandler(socketChannel.alloc()));
        }

        pipeline.addLast("messageSplit", new DelimiterBasedFrameDecoder(81920, Delimiters.lineDelimiter()));
        pipeline.addLast("messageDecoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("messageEncoder", new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast("idleStateHandler", new IdleStateHandler(12, 0, 0, TimeUnit.SECONDS));
        pipeline.addLast("idleStateTrigger",  new AcceptorIdleStateTrigger());
        // and then business logic.
        pipeline.addLast("messageHandler", new ServerDataHandler(messageListener, channelListener));
    }
}
