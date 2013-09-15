package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;

public class Packet02UpdateFinished implements Packet {

    @Override
    public PacketType getPacketType() {
        return PacketType.UPDATE_FINISHED;
    }

    @Override
    public void writeTo(ByteBuf buf) {
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
