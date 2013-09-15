package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;

public class Packet12SectorDiscovery implements Packet {

    private final int sectorX;
    private final int sectorZ;

    public Packet12SectorDiscovery(ByteBuf buf) {
        this.sectorX = buf.readInt();
        this.sectorZ = buf.readInt();
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.SECTOR_DISCOVERY;
    }

    @Override
    public void writeTo(ByteBuf buf) {
        buf.writeInt(sectorX);
        buf.writeInt(sectorZ);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
