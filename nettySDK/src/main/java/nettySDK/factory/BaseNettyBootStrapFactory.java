package nettySDK.factory;

import nettySDK.API.NettyBootStrap;

/**
 * Created by lx on 2017/9/14.
 */
public class BaseNettyBootStrapFactory {
    protected   boolean isKeepAlive = true;
    protected  int port;
    protected  String hostAdress;


    public BaseNettyBootStrapFactory(String hostAdress,int port,boolean isKeepAlive){
        this.isKeepAlive = isKeepAlive;
        this.port = port;
        this.hostAdress = hostAdress;
    }

    public BaseNettyBootStrapFactory(String hostAdress,int port){
        this.port = port;
        this.hostAdress = hostAdress;
    }

    public BaseNettyBootStrapFactory(int port){
        this.port = port;
    }

    public BaseNettyBootStrapFactory(int port,boolean isKeepAlive){
        this.port = port;
        this.isKeepAlive = isKeepAlive;
    }

    public boolean isKeepAlive() {
        return isKeepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        isKeepAlive = keepAlive;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostAdress() {
        return hostAdress;
    }

    public void setHostAdress(String hostAdress) {
        this.hostAdress = hostAdress;
    }
}
