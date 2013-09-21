package org.glydar.protocol.util;

import org.glydar.protocol.RemoteType;

import io.netty.buffer.ByteBuf;

public interface BufWritable {

    void writeTo(RemoteType receiver, ByteBuf buf);
}
