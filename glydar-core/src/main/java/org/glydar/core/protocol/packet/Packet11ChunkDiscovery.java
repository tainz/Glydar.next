package org.glydar.core.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.core.model.geom.IntVector2;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;
import org.glydar.core.protocol.codec.GeomCodec;

public class Packet11ChunkDiscovery implements Packet {

    private final IntVector2 position;

    public Packet11ChunkDiscovery(ByteBuf buf) {
        this.position = GeomCodec.readIntVector2(buf);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.CHUNK_DISCOVERY;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        GeomCodec.writeIntVector2(buf, position);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }

    public IntVector2 getPosition() {
        return position;
    }
}
