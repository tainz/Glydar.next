package org.glydar.core.protocol;

import org.glydar.core.protocol.util.BufWritable;

public interface Packet extends BufWritable {

    PacketType getPacketType();

    <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T client);
}
