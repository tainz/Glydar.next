package org.glydar.core.protocol.util;

import io.netty.buffer.ByteBuf;

import org.glydar.core.protocol.RemoteType;

public interface BufWritable {

    void writeTo(RemoteType receiver, ByteBuf buf);
}
