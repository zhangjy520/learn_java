package com.wulianedu.netty.server.socket;

import com.wulianedu.netty.listener.MessageListener;
import com.wulianedu.netty.listener.ServerSendMsgCallback;
import com.wulianedu.netty.listener.ServerChannelListener;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by connli on 2017/2/4.
 */
public class ServerSocketBootstrap implements ServerChannelListener, MessageListener {

    private MessageListener messageListener;
    private ServerSendMsgCallback failListener;
    private final int port;

    private Map<String, Channel> channels;

    public ServerSocketBootstrap(MessageListener listener,
                                 ServerSendMsgCallback failListener,
                                 int port) {
        this.messageListener = listener;
        this.failListener = failListener;
        this.port = port;
    }

    public void init() {
        Runnable startServerTask = new Runnable() {
            @Override
            public void run() {
                try {
                    // init collect data standard
                    // 启动 netty server
                    start(); // 以后都阻塞
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(startServerTask);
    }

    private void start() throws Exception {
        SelfSignedCertificate ssc = new SelfSignedCertificate();
        SslContext sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey())
                .build();

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ServerHandlerInitializer(this, this, sslCtx));

            // TODO ...
            b.bind(port).sync().channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * ChannelListener
     *
     * @param channels
     */
    @Override
    public void setServerChannels(Map<String, Channel> channels) {
        this.channels = channels;
    }

    public void sendMessage(final String identify, String msg, final int code) {
        sendMessage(identify, msg, code, null);
    }

    public void sendMessage(final String identify, String msg, final int code, final Object ext) {
        if (null == channels || channels.size() == 0) {
            return;
        }
        Channel channel = channels.get(identify);
        if (null != channel) {
            final String message = msg + "\r\n";
            if (null != failListener) {
                channel.writeAndFlush(message).addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (!future.isSuccess()) {
                            failListener.failureMessage(identify, null == ext ? msg : ext, code);
                        }
                    }
                });
            } else {
                channel.writeAndFlush(message);
            }

        }
    }

    public boolean channelStatus(String identify) {
        if (null == channels || channels.size() == 0) {
            return false;
        }

        boolean rst = false;
        Channel channel = channels.get(identify);
        if (null != channel && channel.isActive()) {
            rst = true;
        }
        return rst;
    }

    @Override
    public void onMessageReceive(String msg) {
        if (null != messageListener) {
            messageListener.onMessageReceive(msg);
        }
    }

}
