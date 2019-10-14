package com.coder.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 输出接收到的消息
 * @author Coder
 *
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 收到数据时调用
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf in = (ByteBuf)msg;
            System.out.print(in.toString(CharsetUtil.UTF_8));
        } finally {
            // 抛弃收到的数据
            ReferenceCountUtil.release(msg);
        }
//
//        StringBuilder builder = new StringBuilder()
//                .append("HTTP/1.1 200 OK").append("\n")
//                .append("server: MyNetty1.1").append("\n")
//                .append("content-type: text/html; charset=utf-8").append("\n")
//                .append("Date: Sat, 12 Oct 2019 07:31:39 GMT").append("\n")
//                .append("status: 200").append("\n").append("\n")
//                .append("body");


//        ctx.write(builder.toString());


//        FullHttpResponse response = new DefaultFullHttpResponse(
//                HttpVersion.HTTP_1_1,
//                HttpResponseStatus.OK,
//                Unpooled.wrappedBuffer("欢迎来到猿天地".getBytes("utf-8")));
//        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain;charset=UTF-8");
//        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
//        response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
//        ctx.write(response);

//        ctx.flush();
    }
    
    /**
     * 当Netty由于IO错误或者处理器在处理事件时抛出异常时调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}