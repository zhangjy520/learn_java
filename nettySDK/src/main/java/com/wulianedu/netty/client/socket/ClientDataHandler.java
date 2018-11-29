package com.wulianedu.netty.client.socket;

import com.wulianedu.netty.listener.MessageListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * ClientDataHandler
 */
@ChannelHandler.Sharable
public class ClientDataHandler extends SimpleChannelInboundHandler<String> {

    private MessageListener messageListener;

    public ClientDataHandler(MessageListener listener) {
        this.messageListener = listener;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("connect listener active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connect listener inactive");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {

        try {
            if (null != messageListener) {
                messageListener.onMessageReceive(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
    }

}
