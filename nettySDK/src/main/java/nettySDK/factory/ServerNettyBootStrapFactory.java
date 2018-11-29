package nettySDK.factory;


import nettySDK.API.NettyBootStrap;
import nettySDK.API.NettyClientBootStrap;
import nettySDK.API.NettyServerBootStrap;
import nettySDK.bootstrap.BootStrapClient;
import nettySDK.bootstrap.BootStrapService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lx on 2017/9/14.
 */
public class ServerNettyBootStrapFactory {

    static Map<String,BootStrapService> map;
    static public NettyBootStrap getNettyBootStrap(int port, boolean isKeepAlive) {
        if (map != null){
            BootStrapService bootstrapServer = map.get(port+"");
            if (bootstrapServer == null){
                bootstrapServer = new BootStrapService(isKeepAlive,port);
                map.put(port+"",bootstrapServer);
                NettyBootStrap nettyBootStrap = new NettyServerBootStrap(bootstrapServer);
                return nettyBootStrap;
            }else {
                NettyBootStrap nettyBootStrap = new NettyServerBootStrap(bootstrapServer);
                return nettyBootStrap;
            }
        }else {
            BootStrapService bootStrapService = new BootStrapService(isKeepAlive,port);
            map = new HashMap<>();
            map.put(port+"",bootStrapService);
            NettyBootStrap nettyBootStrap = new NettyServerBootStrap(bootStrapService);
            return nettyBootStrap;
        }
    }
}
