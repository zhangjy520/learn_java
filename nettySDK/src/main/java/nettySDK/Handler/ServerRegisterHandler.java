package nettySDK.Handler;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import nettySDK.API.RegisterProtocol;
import nettySDK.common.type.MessageType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lx on 2017/9/15.
 */
@ChannelHandler.Sharable
public class ServerRegisterHandler extends ChannelInboundHandlerAdapter {
    private int connectNum;
    private Map<String,Channel> channelMap;
    private ChannelGroup recipients =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        recipients.add(ctx.channel());
        connectNum = connectNum+1;
        ctx.fireChannelActive();
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        connectNum = connectNum-1;
        recipients.remove(ctx.channel());
        ctx.fireChannelInactive();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf buf = (ByteBuf) msg;
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String body = new String(req, "UTF-8");
            buf.resetReaderIndex();
            Gson gson = new Gson();
            RegisterProtocol registerProtocol = gson.fromJson(body, RegisterProtocol.class);
            if (registerProtocol.getProtocolName() == MessageType.register.getStatenum()) {
                if (channelMap == null) {
                    channelMap = new HashMap<String, Channel>();
                }
                channelMap.put(registerProtocol.getId(), ctx.channel());
            }
        }catch (Exception e){

        }finally {

            ctx.fireChannelRead(msg);
        }
    }

    public int getConnectNum() {
        return connectNum;
    }

    public void setConnectNum(int connectNum) {
        this.connectNum = connectNum;
    }

    public Map<String, Channel> getChannelMap() {
        return channelMap;
    }

    public void setChannelMap(Map<String, Channel> channelMap) {
        this.channelMap = channelMap;
    }

    public ChannelGroup getRecipients() {
        return recipients;
    }

    public void setRecipients(ChannelGroup recipients) {
        this.recipients = recipients;
    }
}
