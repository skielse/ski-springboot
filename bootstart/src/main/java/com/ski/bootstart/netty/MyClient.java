package com.ski.bootstart.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author wangzijie
 * @date 2023/3/14
 */
public class MyClient {
    private final String host;
    private final int port;

    public MyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /*public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MyClientHandler());
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }*/

    public void run() throws Exception {
        // 设置控制台编码方式为UTF-8
        System.setProperty("file.encoding", "UTF-8");
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MyClientHandler());
                        }
                    });

            Channel channel = bootstrap.connect().sync().channel();
            Scanner scanner = new Scanner(System.in, "UTF-8");
            while (true) {
                String line = scanner.nextLine();
                ByteBuf buf = Unpooled.copiedBuffer(line.getBytes(StandardCharsets.UTF_8));
                channel.writeAndFlush(buf);
            }
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 65150;
        new MyClient(host, port).run();
    }
}
