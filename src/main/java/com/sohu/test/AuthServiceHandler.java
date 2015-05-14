package com.sohu.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * User: wujian (jianwu203076@sohu-inc.com)
 * Date: 05/13/2015 15:54
 */
public class AuthServiceHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        String str = (String) msg;
//        System.out.println("channelRead : " + str);
//        String back = "world";
//        ByteBuf resp = Unpooled.copiedBuffer(back.getBytes());
//        ctx.write(resp);
        System.out.println("channelRead begin");
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
//        int length = DataUtil.getInt(req, 0);
//        int version = (int) req[4];
//        int action = (int) req[5];
//        int emailLength = (int) req[6];
//        String email = new String(req, 7, emailLength);
//        int passwordLength = (int) req[7 + emailLength];
//        String password = new String(req, 8 + emailLength, passwordLength);
//        int soSigOffset = 8 + emailLength + passwordLength;
//        String so_sig = new String(req, 8 + emailLength + passwordLength, req.length - soSigOffset);

        int version = (int) req[0];
        int action = (int) req[1];
        int length = DataUtil.getInt(req, 2);
        int emailLength = (int) req[6];
        String email = new String(req, 7, emailLength);
        int passwordLength = (int) req[7 + emailLength];
        String password = new String(req, 8 + emailLength, passwordLength);
        int soSigOffset = 8 + emailLength + passwordLength;
        String so_sig = new String(req, 8 + emailLength + passwordLength, req.length - soSigOffset);

        System.out.println("length : " + length);
        System.out.println("req.length : " + req.length);
        System.out.println("version : " + version);
        System.out.println("action : " + action);
        System.out.println("email : " + email);
        System.out.println("password : " + password);
        System.out.println("so_sig : " + so_sig);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception{
        ctx.close();
    }
}
