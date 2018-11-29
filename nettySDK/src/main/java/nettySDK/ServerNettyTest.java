package nettySDK;


import nettySDK.API.MessageHandler;
import nettySDK.API.NettyBootStrap;
import nettySDK.factory.ServerNettyBootStrapFactory;

/**
 * Created by lx on 2017/9/14.
 */
public class ServerNettyTest {
    public static void main(String[] args) throws Exception {
        NettyBootStrap nettyBootStrap = ServerNettyBootStrapFactory.getNettyBootStrap(9564,true);
        nettyBootStrap.connect();
        nettyBootStrap.sendMessage("server send message1\r\n");
        nettyBootStrap.listenerMessage(new MessageHandler() {
            @Override
            public void onMessageReceive(Object message) {

            }
        });
    }
}
