package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;

public class Packet04WorldUpdate implements Packet {

    private final byte[] rawData;

    // private final WorldUpdateData data;

    public Packet04WorldUpdate(ByteBuf buf) {
        int length = buf.readInt();
        this.rawData = new byte[length];
        buf.readBytes(rawData);
        // ByteBuf decompressed = ZLibOperations.decompress(buf);
        // this.data = new WorldUpdateData(decompressed);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.WORLD_UPDATE;
    }

    @Override
    public void writeTo(ByteBuf buf) {
        buf.writeInt(rawData.length);
        buf.writeBytes(rawData);
        // ZLibOperations.compress(buf, data);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
