package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.api.entity.EntityData;
import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.RemoteType;
import org.glydar.protocol.codec.EntityCodec;
import org.glydar.protocol.util.BufWritable;
import org.glydar.protocol.util.ZLibOperations;

public class Packet00EntityUpdate implements Packet {

    private final long       entityId;
    private final EntityData data;

    public Packet00EntityUpdate(ByteBuf buf) {
        ByteBuf decompressed = ZLibOperations.decompress(buf);
        this.entityId = decompressed.readLong();
        this.data = EntityCodec.readEntityData(decompressed);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.ENTITY_UPDATE;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        ZLibOperations.compress(receiver, buf, new EntityUpdate());
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }

    public long getEntityId() {
        return entityId;
    }

    public EntityData getData() {
        return data;
    }

    private class EntityUpdate implements BufWritable {

        @Override
        public void writeTo(RemoteType receiver, ByteBuf buf) {
            buf.writeLong(entityId);
            EntityCodec.writeEntityData(buf, data);
        }
    }
}
