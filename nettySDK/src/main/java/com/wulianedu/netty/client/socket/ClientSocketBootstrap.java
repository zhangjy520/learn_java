package com.wulianedu.netty.client.socket;

import com.wulianedu.netty.client.socket.heart.ClientConnectListener;
import com.wulianedu.netty.listener.ClientChannelListener;
import com.wulianedu.netty.listener.MessageListener;
import com.wulianedu.netty.listener.ClientSendMsgCallback;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by connli on 2017/2/4.
 */
public class ClientSocketBootstrap implements ClientChannelListener {

    private MessageListener messageListener;
    private ClientSendMsgCallback failListener;

    private SslContext sslCtx;
    private String identify;
    private String host;
    private int port;
    private Channel finalChannel;

    private Bootstrap bootstrap;
    private EventLoopGroup loopGroup;
    private ClientConnectListener connectHandler;
    private Thread currentThread;

    private final ClientSocketBootstrap selfObject;

    public ClientSocketBootstrap(MessageListener messageListener,
                                 ClientSendMsgCallback failListener,
                                 String identify,
                                 String host,
                                 int port) {
        this.messageListener = messageListener;
        this.failListener = failListener;
        this.identify = identify;
        this.host = host;
        this.port = port;
        selfObject = this;

        try {
            sslCtx = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } catch (Exception e) {
            System.out.println("ssl init error:" + e.getMessage());
        }
    }

    public void init() {

        Runnable startServerTask = new Runnable() {
            @Override
            public void run() {
                try {
                    // 启动 netty server
                    start();
                    currentThread = Thread.currentThread();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(startServerTask);

    }

    private void start() throws Exception {

        loopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(loopGroup).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO));
        connectHandler = new ClientConnectListener(sslCtx,
                selfObject, messageListener, bootstrap, port, host);

        while (true) {
            ChannelFuture future;
            //进行连接
            try {
                synchronized (bootstrap) {
                    bootstrap.handler(new ChannelInitializer<Channel>() {

                        //初始化channel
                        @Override
                        protected void initChannel(Channel ch) throws Exception {

                            ChannelHandler[] holders = null;
                            if (null != sslCtx) {
                                holders = ChannelHandlerHolder.getClientHandlers(messageListener, connectHandler, true);
                                holders[0] = sslCtx.newHandler(ch.alloc(), host, port);
                            } else {
                                holders = ChannelHandlerHolder.getClientHandlers(messageListener, connectHandler, false);
                            }
                            ch.pipeline().addLast(holders);
                        }
                    });

                    future = bootstrap.connect(host, port).sync();
                    setClientChannel(future.channel());
                }

                // 以下代码在synchronized同步块外面是安全的
                if (future.isSuccess() && future.channel().isActive()) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            // 如果不退出，等待10秒钟
            try {
                Thread.sleep(ChannelHandlerHolder.TIME_INTERVAL);
            } catch (Exception e) {}
        }
    }

    public void sendMessage(final String msg, final int code) {
        sendMessage(msg, code, null);
    }

    public void sendMessage(final String msg, final int code, final Object ext) {
        try {
            String message = msg + "\r\n";
            if (null != finalChannel && finalChannel.isActive()) {
                if (null != failListener) {
                    finalChannel.writeAndFlush(message).addListener(new GenericFutureListener<Future<? super Void>>() {
                        @Override
                        public void operationComplete(Future<? super Void> future) throws Exception {
                            if (!future.isSuccess()) {
                                failListener.failureMessage(null == ext ? msg : ext, code);
                            }
                        }
                    });
                } else {
                    finalChannel.writeAndFlush(message);
                }
            }
        } catch (Exception e) {
            System.out.println("send msg error:" + e.getMessage());
        }
    }

    public boolean channelStatus() {
        boolean rst = false;

        if (null != finalChannel && finalChannel.isActive()) {
            rst = true;
        }
        return rst;
    }

    /**
     * channelListener
     *
     * @param channel
     */
    @Override
    public void setClientChannel(Channel channel) {
        if (null != channel && channel.isActive()) {
            this.finalChannel = channel;
            sendMessage("init_c_channel:" + identify, -1);
        }
    }

    public void closeChannel() {

        if (null != messageListener) {
            messageListener = null;
        }

        // 关闭定时器
        if (null != connectHandler) {
            connectHandler.closeTimer();
            connectHandler = null;
        }

        // 关闭channel
        if (null != finalChannel) {
            finalChannel.disconnect();
            finalChannel.closeFuture();
            finalChannel = null;
        }

        // 关闭 group等
        loopGroup = null;
        bootstrap = null;

        // 关闭、销毁线程
        if (null != currentThread) {
            currentThread.interrupt();
            currentThread = null;
        }
    }
}
