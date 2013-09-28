package org.glydar.protocol;

import org.glydar.protocol.util.BufWritable;

public interface Packet extends BufWritable {

    PacketType getPacketType();

    <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T client);
}
