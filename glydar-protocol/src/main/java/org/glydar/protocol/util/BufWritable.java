package org.glydar.protocol.util;

import io.netty.buffer.ByteBuf;

public interface BufWritable {

    void writeTo(ByteBuf buf);
}
