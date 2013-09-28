package org.glydar.core.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;

/* Structures and data discovered by cuwo (http://github.com/matpow2) */
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
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        buf.writeInt(seed);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }

    public int getSeed() {
        return seed;
    }
}
