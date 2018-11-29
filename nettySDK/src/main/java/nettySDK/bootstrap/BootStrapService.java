package nettySDK.bootstrap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import nettySDK.Handler.ServerHandler;
import nettySDK.Handler.ServerRegisterHandler;

import java.nio.charset.Charset;

/**
 * Created by lx on 2017/5/2.
 */
public class BootStrapService {
    private boolean isKeepAlive = true;
    private int port;
    private ServerHandler serverHandler = new ServerHandler();
    private ServerRegisterHandler serverRegisterHandler = new ServerRegisterHandler();
    private boolean isSuccess = false;

    public BootStrapService(boolean isKeepAlive, int port) {
        this.isKeepAlive = isKeepAlive;
        this.port = port;
    }

    public void connect() throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 配置服务器的NIO线程租
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());

            // 绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            System.out.println("服务器启动成功！");
            f.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()){
                        isSuccess = true;
                    }else {
                        isSuccess = false;
                    }
                }
            });
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            Thread.sleep(5000);
            connect();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel arg0) throws Exception {
            System.out.println("服务器初始化");
            arg0.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(81920, Delimiters.lineDelimiter()));
            arg0.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
            arg0.pipeline().addLast(serverRegisterHandler);
            arg0.pipeline().addLast(serverHandler);
            arg0.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
        }
    }

    public ServerHandler getServerHandler() {
        return serverHandler;
    }

    public void setServerHandler(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

    public ServerRegisterHandler getServerRegisterHandler() {
        return serverRegisterHandler;
    }

    public void setServerRegisterHandler(ServerRegisterHandler serverRegisterHandler) {
        this.serverRegisterHandler = serverRegisterHandler;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
