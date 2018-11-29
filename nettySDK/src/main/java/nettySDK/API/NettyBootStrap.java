package nettySDK.API;

/**
 * Created by lx on 2017/9/14.
 */
public interface NettyBootStrap {
    void sendMessage(Object object) throws Exception;
    void sendMessage(String id,Object object) throws Exception;
    void listenerMessage(MessageHandler messageHandler);
    void connect() throws Exception;
    void connect(String id) throws Exception;
    void waitClose() throws InterruptedException;
}
