package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.RemoteType;

public class Packet08Stealth implements Packet {

    private final long   id;
    private final byte[] unknowndata;

    public Packet08Stealth(ByteBuf buf) {
        id = buf.readLong();
        this.unknowndata = new byte[32];
        buf.readBytes(unknowndata);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.STEALTH;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        buf.writeLong(id);
        buf.writeBytes(unknowndata);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
