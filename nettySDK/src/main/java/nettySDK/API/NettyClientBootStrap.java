package nettySDK.API;


import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import nettySDK.Handler.ClientHandler;
import nettySDK.bootstrap.BootStrapClient;
import nettySDK.common.type.MessageType;


import java.util.Date;

/**
 * Created by lx on 2017/9/14.
 */
public class NettyClientBootStrap implements NettyBootStrap {
    private BootStrapClient bootStrapClient;
    private ChannelHandler channelHandler;

    public NettyClientBootStrap(BootStrapClient bootStrapClient) {
        if (bootStrapClient == null) {
            throw new NullPointerException("bootStrapClient is null");
        }
        this.bootStrapClient = bootStrapClient;
        this.channelHandler = bootStrapClient.getClientHandler();
    }

    @Override
    public void sendMessage(Object object) throws Exception {
        object = object+"\r\n";
        ClientHandler clientHandler = (ClientHandler) channelHandler;
        clientHandler.getCtx().channel().writeAndFlush(object);
    }

    @Override
    public void sendMessage(String id, Object object) throws Exception {
        throw new Exception("not allowed to have id");
    }

    @Override
    public void listenerMessage(MessageHandler messageHandler) {
        ClientHandler clientHandler = (ClientHandler) channelHandler;
        clientHandler.setMessageHandler(messageHandler);
    }

    @Override
    public void connect() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bootStrapClient.connect(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (!thread.isAlive()){
            thread.start();
        }

         do {

         }while (!bootStrapClient.isSuccess());
    }

    @Override
    public void connect(String id) throws Exception {
        final RegisterProtocol registerProtocol = new RegisterProtocol();
        registerProtocol.setId(id);
        registerProtocol.setTime(new Date().getTime());
        registerProtocol.setProtocolName(MessageType.register.getStatenum());
        final Gson gson = new Gson();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bootStrapClient.connect(gson.toJson(registerProtocol)+"\r\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if (!thread.isAlive()){
            thread.start();
        }
    }

    @Override
    public void waitClose() throws InterruptedException {

    }
}
