package nettySDK;


import nettySDK.API.MessageHandler;
import nettySDK.API.NettyBootStrap;
import nettySDK.factory.ClientNettyBootStrapFactory;

/**
 * Created by lx on 2017/9/14.
 */
public class ClientNettyTest {
    public static void main(String[] args) throws Exception {
        NettyBootStrap nettyBootStrap = ClientNettyBootStrapFactory.getNettyBootStrap("127.0.0.1",9564,true);
        nettyBootStrap.connect();
        nettyBootStrap.sendMessage("aaa");
        nettyBootStrap.sendMessage("aaa");

        nettyBootStrap.listenerMessage(new MessageHandler() {
            @Override
            public void onMessageReceive(Object message) {
               // todo ...
                System.out.println(message);
            }
        });
    }
}
