package com.ski.bootstart.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import javax.swing.*;
import java.awt.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NettyClientDemo extends JFrame {

    private JTextArea textArea;
    private JTextField textField;
    private JButton sendButton;

    private EventLoopGroup group;
    private Bootstrap bootstrap;

    private Channel channel;
    private ChannelHandlerContext ctx;

    public NettyClientDemo() throws InterruptedException {
        setTitle("Netty Client Demo");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建消息显示区域
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // 创建消息发送区域
        JPanel sendPanel = new JPanel();
        sendPanel.setLayout(new BorderLayout());
        textField = new JTextField();
        sendPanel.add(textField, BorderLayout.CENTER);
        sendButton = new JButton("发送");
        sendPanel.add(sendButton, BorderLayout.EAST);
        add(sendPanel, BorderLayout.SOUTH);

        // 绑定事件
        sendButton.addActionListener(e -> sendMessage());
//        textField.addActionListener(e -> sendMessage());

        // 初始化Netty客户端
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("localhost", 65150))
                .handler(new MyClientInitializer());
        channel = bootstrap.connect().sync().channel();
        // 显示窗口
        setVisible(true);
    }

    private void sendMessage() {
        String message = textField.getText();
        if (message != null && !message.isEmpty()) {
            if (channel != null) {
                channel.writeAndFlush(message);
            }
            textField.setText("");
        }
    }

    private void printMessage(String message) {
        textArea.append(message + "\n");
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyClientDemo();
    }


    private class MyClientHandler extends ChannelInboundHandlerAdapter {
        private SwingWorker<String, List<String>> worker;
        private JTextArea textArea;

        public MyClientHandler(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            ctx.pipeline().remove(new MyClientInitializer()); // 移除初始化器
            worker = new SwingWorker<String, List<String>>() {
                @Override
                protected String doInBackground() throws Exception {
                    while (!Thread.currentThread().isInterrupted()) {
                        String input = JOptionPane.showInputDialog("请输入要发送的消息：");
                        if (input == null || input.isEmpty()) {
                            continue;
                        }
                        ctx.writeAndFlush(Unpooled.copiedBuffer(input, CharsetUtil.UTF_8));
                    }
                    return null;
                }

                @Override
                protected void done() {
                    super.done();
                    ctx.close();
                }

                @Override
                public void process(List<List<String>> chunks) {
                    super.process(chunks);
                    for (List<String> lines : chunks) {
                        for (String line : lines) {
                            textArea.append(line + "\n");
                        }
                    }
                }
            };
            worker.execute();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            String input = buf.toString(CharsetUtil.UTF_8);
            SwingUtilities.invokeLater(() -> {
                printMessage(input);
            });
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
            ctx.close();
        }
    }


    private class MyClientInitializer extends ChannelInitializer<SocketChannel> {

//        @Override
//        public void channelActive(ChannelHandlerContext ctx) throws Exception {
//            super.channelActive(ctx);
//            ctx.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
//            ctx.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
//            ctx.pipeline().addLast(new MyClientHandler(textArea));
//        }

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
            ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
            ch.pipeline().addLast(new MyClientHandler(textArea));
        }
    }
}