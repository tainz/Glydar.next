package org.glydar.protocol;

import io.netty.buffer.ByteBuf;

public interface Packet {

    PacketType getPacketType();

    void writeTo(ByteBuf buf);

    <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T client);
}
