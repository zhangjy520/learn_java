package nettySDK.factory;


import nettySDK.API.NettyBootStrap;
import nettySDK.API.NettyClientBootStrap;
import nettySDK.bootstrap.BootStrapClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lx on 2017/9/14.
 */
public class ClientNettyBootStrapFactory{
    static Map<String,BootStrapClient> map;
    static public NettyBootStrap getNettyBootStrap(String hostAdress,int port, boolean isKeepAlive) {
        if (map != null){
            BootStrapClient bootStrapClient = map.get(hostAdress+port);
            if (bootStrapClient == null){
                bootStrapClient = new BootStrapClient(hostAdress,port,isKeepAlive);
                map.put(hostAdress+port,bootStrapClient);
                NettyBootStrap nettyBootStrap = new NettyClientBootStrap(bootStrapClient);
                return nettyBootStrap;
            }else {
                NettyBootStrap nettyBootStrap = new NettyClientBootStrap(bootStrapClient);
                return nettyBootStrap;
            }
        }else {
            BootStrapClient bootStrapClient = new BootStrapClient(hostAdress,port,isKeepAlive);
            map = new HashMap<>();
            map.put(hostAdress+port,bootStrapClient);
            NettyBootStrap nettyBootStrap = new NettyClientBootStrap(bootStrapClient);
            return nettyBootStrap;
        }
    }
}
