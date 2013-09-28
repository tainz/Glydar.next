package org.glydar.core.protocol.util;

import org.glydar.core.protocol.RemoteType;

import io.netty.buffer.ByteBuf;

public interface BufWritable {

    void writeTo(RemoteType receiver, ByteBuf buf);
}
