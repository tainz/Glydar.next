package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.api.geom.IntVector2;
import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.RemoteType;
import org.glydar.protocol.codec.GeomCodec;

public class Packet12SectorDiscovery implements Packet {

    private final IntVector2 position;

    public Packet12SectorDiscovery(ByteBuf buf) {
        this.position = GeomCodec.readIntVector2(buf);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.SECTOR_DISCOVERY;
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
