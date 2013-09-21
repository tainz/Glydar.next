package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.data.WorldUpdateData;
import org.glydar.protocol.util.ZLibOperations;

public class Packet04WorldUpdate implements Packet {

    private final WorldUpdateData data;

    public Packet04WorldUpdate(WorldUpdateData data) {
        this.data = data;
    }

    public Packet04WorldUpdate(ByteBuf buf) {
        ByteBuf decompressed = ZLibOperations.decompress(buf);
        this.data = new WorldUpdateData(decompressed);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.WORLD_UPDATE;
    }

    @Override
    public void writeTo(ByteBuf buf) {
        ZLibOperations.compress(buf, data);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
