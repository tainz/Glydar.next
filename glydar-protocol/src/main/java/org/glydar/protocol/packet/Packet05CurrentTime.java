package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.RemoteType;

public class Packet05CurrentTime implements Packet {

    private final long day;
    private final long time;

    public Packet05CurrentTime(long day, long time) {
        this.day = day;
        this.time = time;
    }

    public Packet05CurrentTime(ByteBuf buf) {
        this.day = buf.readUnsignedInt();
        this.time = buf.readUnsignedInt();
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.CURRENT_TIME;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        buf.writeInt((int) day);
        buf.writeInt((int) time);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
