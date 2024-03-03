package org.idea.jrpc.framework.core.common;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import static org.idea.jrpc.framework.core.common.constants.RpcConstants.MAGIC_NUMBER;

public class RpcDecoder extends ByteToMessageDecoder {

    // Header length
    public static final int BASE_LENGTH = 8;


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        // 1. reject big packet
        if (byteBuf.readableBytes() > 1000) {
            byteBuf.skipBytes(byteBuf.readableBytes());
        }

        int beginReader;
        while (true) {
            beginReader = byteBuf.readerIndex();
            byteBuf.markReaderIndex();
            if (byteBuf.readShort() == MAGIC_NUMBER) {
                break;
            } else {
                // this packet is not from legal client
                ctx.close();
                return;
            }
        }

        int length = byteBuf.readInt();
        // uncompleted packet, we need to reset the readerIndex
        if (byteBuf.readableBytes() < length) {
            byteBuf.readerIndex(beginReader);
            return;
        }

        // The real content
        byte[] data = new byte[length];
        byteBuf.readBytes(data);
        RpcProtocol rpcProtocol = new RpcProtocol(data);

        out.add(rpcProtocol);
    }
}