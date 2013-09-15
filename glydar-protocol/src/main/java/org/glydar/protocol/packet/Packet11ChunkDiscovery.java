package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;

public class Packet11ChunkDiscovery implements Packet {

    private final int chunkX;
    private final int chunkZ;

    public Packet11ChunkDiscovery(ByteBuf buf) {
        chunkX = buf.readInt();
        chunkZ = buf.readInt();
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.CHUNK_DISCOVERY;
    }

    @Override
    public void writeTo(ByteBuf buf) {
        buf.writeInt(chunkX);
        buf.writeInt(chunkZ);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
