package com.wulianedu.netty.client.socket.heart;

import com.wulianedu.netty.client.socket.ChannelHandlerHolder;
import com.wulianedu.netty.listener.ClientChannelListener;
import com.wulianedu.netty.listener.MessageListener;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.handler.ssl.SslContext;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * 重连检测，进行重连，5秒一次
 */
@ChannelHandler.Sharable
public class ClientConnectListener extends ChannelInboundHandlerAdapter implements TimerTask {

    private final HashedWheelTimer timer = new HashedWheelTimer();

    private ClientChannelListener channelListener;
    private MessageListener messageListener;
    private SslContext sslCtx;
    private final Bootstrap bootstrap;
    private final String host;
    private final int port;

    private final ClientConnectListener selfObject;

    public ClientConnectListener(SslContext sslContext,
                                 ClientChannelListener channelListener,
                                 MessageListener messageListener,
                                 Bootstrap bootstrap, int port, String host) {
        this.sslCtx = sslContext;
        this.channelListener = channelListener;
        this.messageListener = messageListener;
        this.bootstrap = bootstrap;
        this.port = port;
        this.host = host;
        selfObject = this;
    }

    /**
     * channel链路每次active的时候，将其连接的次数重新☞ 0
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("reconnect listener active");
        ctx.fireChannelActive();
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("reconnect listener inactive");
        if(true) { // android 可以判断当前设备是否有网络连接 TODO...
            timer.newTimeout(this, ChannelHandlerHolder.TIME_INTERVAL, TimeUnit.MILLISECONDS);
        }
        ctx.fireChannelInactive();
    }
    

    public void run(Timeout timeout) throws Exception {

        if (null == bootstrap) {
            return;
        }
        ChannelFuture future;
        synchronized (bootstrap) {
            bootstrap.handler(new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ChannelHandler[] handlers = null;
                    if (null == sslCtx) {
                        handlers = ChannelHandlerHolder.getClientHandlers(messageListener, selfObject, false);
                    } else {
                        handlers = ChannelHandlerHolder.getClientHandlers(messageListener, selfObject, true);;
                        handlers[0] = sslCtx.newHandler(ch.alloc(), host, port);
                    }
                    ch.pipeline().addLast(handlers);
                }
            });
            future = bootstrap.connect(host, port);
        }
        //future对象
        future.addListener(new ChannelFutureListener() {

            public void operationComplete(ChannelFuture f) throws Exception {
                boolean succeed = f.isSuccess();
                //如果重连失败，则调用ChannelInactive方法，再次出发重连事件
                if (!succeed) {
                    f.channel().pipeline().fireChannelInactive();
                } else {
                    channelListener.setClientChannel(f.channel());
                }
            }
        });
        
    }

    public void closeTimer() {
        if (null != timer) {
            timer.stop();
        }
    }
}
