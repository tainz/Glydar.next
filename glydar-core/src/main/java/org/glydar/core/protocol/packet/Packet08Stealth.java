package org.glydar.core.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;

/* Structures and data discovered by cuwo (http://github.com/matpow2) */
public class Packet08Stealth implements Packet {

    private final long   id;
    private final byte[] unknownData;

    public Packet08Stealth(ByteBuf buf) {
        id = buf.readLong();
        this.unknownData = new byte[32];
        buf.readBytes(unknownData);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.STEALTH;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        buf.writeLong(id);
        buf.writeBytes(unknownData);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }

    public long getId() {
        return id;
    }

    public byte[] getUnknownData() {
        return unknownData;
    }
}
