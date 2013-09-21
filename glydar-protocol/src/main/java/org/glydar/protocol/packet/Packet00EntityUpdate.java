package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.api.entity.Entity;
import org.glydar.api.entity.EntityChanges;
import org.glydar.api.entity.EntityData;
import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.data.DataCodec;
import org.glydar.protocol.util.BufWritable;
import org.glydar.protocol.util.ZLibOperations;

public class Packet00EntityUpdate implements Packet {

    private final long          entityId;
    private final EntityChanges changes;
    private final EntityData    data;

    public Packet00EntityUpdate(Entity entity) {
        this.entityId = entity.getId();
        this.changes = entity.getChanges();
        this.data = entity.getData();
    }

    public Packet00EntityUpdate(ByteBuf buf) {
        ByteBuf decompressed = ZLibOperations.decompress(buf);
        this.entityId = decompressed.readLong();
        this.changes = DataCodec.readEntityChanges(decompressed);
        this.data = DataCodec.readEntityData(decompressed, changes);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.ENTITY_UPDATE;
    }

    @Override
    public void writeTo(ByteBuf buf) {
        ZLibOperations.compress(buf, new EntityUpdate());
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }

    public long getEntityId() {
        return entityId;
    }

    private class EntityUpdate implements BufWritable {

        @Override
        public void writeTo(ByteBuf buf) {
            buf.writeLong(entityId);
            DataCodec.writeEntityChanges(buf, changes);
            DataCodec.writeEntityData(buf, changes, data);
        }
    }
}
