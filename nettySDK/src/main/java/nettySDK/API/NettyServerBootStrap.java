package nettySDK.API;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import nettySDK.Handler.ServerHandler;
import nettySDK.Handler.ServerRegisterHandler;
import nettySDK.bootstrap.BootStrapService;

/**
 * Created by lx on 2017/9/14.
 */
public class NettyServerBootStrap implements NettyBootStrap {
    private BootStrapService bootStrapService;
    private ServerHandler channelHandler;
    private ServerRegisterHandler serverRegisterHandler;
    public NettyServerBootStrap(BootStrapService bootStrapService){
        if (bootStrapService == null){
            throw new NullPointerException("bootStrapService is null");
        }
        this.bootStrapService = bootStrapService;
        this.channelHandler = bootStrapService.getServerHandler();
        this.serverRegisterHandler = bootStrapService.getServerRegisterHandler();
    }

    @Override
    public void sendMessage(Object object) {
        object = object+"\r\n";
        serverRegisterHandler.getRecipients().writeAndFlush(object);
    }

    @Override
    public void sendMessage(String id, Object object) throws Exception {

        object = object+"\r\n";
        Channel channel = serverRegisterHandler.getChannelMap().get(id);
        if (channel == null){
            throw new Exception("channel is null");
        }else {
            channel.writeAndFlush(object);
        }
    }

    @Override
    public void listenerMessage(MessageHandler messageHandler) {
       channelHandler.setMessageHandler(messageHandler);
    }

    @Override
    public void connect() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bootStrapService.connect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if (!thread.isAlive()){
            thread.start();
        }

        do {
            System.out.println("f");
        }while (!bootStrapService.isSuccess());

    }

    @Override
    public void connect(String id) throws Exception {

    }

    @Override
    public void waitClose() throws InterruptedException {

    }
}
