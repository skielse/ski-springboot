package com.ski.bootstart.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangzijie
 * @date 2023/3/14
 */
@Slf4j
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        try {
            StringBuilder info = new StringBuilder();
            while (in.isReadable()) {
                String bytes = String.valueOf((char) in.readByte());
                log.info("msg:>>>>>>{}", bytes);
                info.append(bytes);
                System.out.flush();
            }
            log.info("complete msg:>>>>>>{}", info);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

