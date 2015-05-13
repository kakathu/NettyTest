package com.sohu.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 * User: wujian (jianwu203076@sohu-inc.com)
 * Date: 05/13/2015 16:58
 */
public class NettyClientSendHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf firstMessage;

    public NettyClientSendHandler() {
        byte[] req = "hello".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws  Exception {
        System.out.println("begin send");
        ctx.writeAndFlush(firstMessage);
        System.out.println("end send");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String str = (String) msg;
        System.out.println("client recv : " + str);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        ctx.flush();
    }
}
