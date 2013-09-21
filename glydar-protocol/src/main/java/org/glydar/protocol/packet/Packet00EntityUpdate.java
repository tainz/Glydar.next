package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.RemoteType;

public class Packet00EntityUpdate implements Packet {

    private final byte[] rawData;

    // private final long entityId;
    // private final EntityChanges changes;
    // private final EntityData data;

    public Packet00EntityUpdate(ByteBuf buf) {
        int length = buf.readInt();
        this.rawData = new byte[length];
        buf.readBytes(rawData);
        // ByteBuf decompressed = ZLibOperations.decompress(buf);
        // this.entityId = decompressed.readLong();
        // this.changes = DataCodec.readEntityChanges(decompressed);
        // this.data = DataCodec.readEntityData(decompressed, changes);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.ENTITY_UPDATE;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        buf.writeInt(rawData.length);
        buf.writeBytes(rawData);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }

    // private class EntityUpdate implements BufWritable {
    //
    // @Override
    // public void writeTo(ByteBuf buf) {
    // buf.writeLong(entityId);
    // DataCodec.writeEntityChanges(buf, changes);
    // DataCodec.writeEntityData(buf, changes, data);
    // }
    // }
}
