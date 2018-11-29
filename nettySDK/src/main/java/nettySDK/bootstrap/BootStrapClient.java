package nettySDK.bootstrap;



import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import nettySDK.Handler.ClientHandler;

import java.nio.charset.Charset;

/**
 * Created by lx on 2017/5/2.
 */

public class BootStrapClient {
    private ClientHandler clientHandler = new ClientHandler();
    private boolean isKeepAlive = true;
    private int port;
    private String hostAdress;
    private String idMessage;
    private boolean isSuccess = false;
    private int defeatNum = 1;

    public BootStrapClient(String hostAdress, int port, boolean isKeepAlive) {
        this.isKeepAlive = isKeepAlive;
        this.hostAdress = hostAdress;
        this.port = port;
    }

    public void connect(String idMessage) throws Exception {
        this.idMessage = idMessage;
        defeatNum = 1;
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, false)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel arg0)
                                throws Exception {
                            arg0.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(81920, Delimiters.lineDelimiter()));
                            arg0.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                            arg0.pipeline().addLast(clientHandler);
                            arg0.pipeline().addLast(new IdleStateHandler(20, 10, 0));
                            arg0.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));

                        }
                    });
            // 发起异步连接操作
            ChannelFuture channelFuture = b.connect(hostAdress, port).sync();
            if (idMessage!=null) {
                channelFuture.channel().writeAndFlush(idMessage);
            }
            channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()){
                        isSuccess = true;
                    }else {
                        isSuccess = false;
                    }
                }
            });
            channelFuture.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
            if (defeatNum<10) {
                Thread.sleep(2000 * defeatNum);
                defeatNum++;
                connect(idMessage);
            }
            defeatNum =1;
        }
    }

    public ClientHandler getClientHandler() {
        return clientHandler;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
