package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;

public class Packet15Seed implements Packet {

    private final int seed;

    public Packet15Seed(int seed) {
        this.seed = seed;
    }

    public Packet15Seed(ByteBuf buf) {
        this.seed = buf.readInt();
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.SEED;
    }

    @Override
    public void writeTo(ByteBuf buf) {
        buf.writeInt(seed);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
