package org.glydar.core.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;

/* Structures and data discovered by cuwo (http://github.com/matpow2) */
public class Packet02UpdateFinished implements Packet {

    @Override
    public PacketType getPacketType() {
        return PacketType.UPDATE_FINISHED;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
