package com.wulianedu.netty.server.socket;

import com.wulianedu.netty.listener.MessageListener;
import com.wulianedu.netty.listener.ServerChannelListener;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * ClientDataHandler
 * 消息最终处理者，所以用SimpleChannelInboundHandler没问题，自动释放
 */
public class ServerDataHandler extends SimpleChannelInboundHandler<String> {

    static final Map<String, Channel> channelMap = new HashMap<String, Channel>();

    private MessageListener messageListener;
    private ServerChannelListener channelListener;
    public ServerDataHandler(MessageListener messageListener,
                             ServerChannelListener channelListener) {
        this.messageListener = messageListener;
        this.channelListener = channelListener;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("server channel active....");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server channel inactive....");

        closeClient(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {

        try {
            if (null == msg || msg.trim().length() == 0) {
                return;
            }
            // heartbeat
            if ("ping".equals(msg)) {
                return;
            } else if (msg.startsWith("init_c_channel:")) {
                String identity = msg.split(":")[1];
                if (null != identity && identity.length() > 0) {
                    channelMap.put(identity, channelHandlerContext.channel());
                    if (null != channelListener) {
                        channelListener.setServerChannels(channelMap);
                    }
                }
                return;
            }
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
        // 主动关闭 cancel_connection
        System.out.println(cause.getMessage());
        if ("cancel_connection".equals(cause.getMessage())) {
            closeClient(ctx);
            ctx.channel().close();
            ctx.close();
        }
    }

    public void closeClient(ChannelHandlerContext ctx) {boolean exist = false;
        for (Map.Entry<String, Channel> entry : channelMap.entrySet()) {
            Channel c = entry.getValue();
            if (c == ctx.channel()) {
                channelMap.remove(entry.getKey());
                exist = true;
                break;
            }
        }
        if (exist) {
            channelListener.setServerChannels(channelMap);
        }
    }

}
