package org.glydar.core.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.core.model.entity.CoreEntityData;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;
import org.glydar.core.protocol.codec.EntityCodec;
import org.glydar.core.protocol.util.BufWritable;
import org.glydar.core.protocol.util.ZLibOperations;

/* Structures and data discovered by cuwo (http://github.com/matpow2) */
public class Packet00EntityUpdate implements Packet {

    private final long entityId;
    private final CoreEntityData data;

    public Packet00EntityUpdate(ByteBuf buf) {
        ByteBuf decompressed = ZLibOperations.decompress(buf);
        this.entityId = decompressed.readLong();
        this.data = EntityCodec.readEntityData(decompressed);
    }

    public Packet00EntityUpdate(long entityId, CoreEntityData data) {
        this.entityId = entityId;
        this.data = data;
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

    public CoreEntityData getData() {
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
