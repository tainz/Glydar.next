package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteOrder;
import java.util.Arrays;

import org.glydar.api.Glydar;
import org.glydar.api.entity.Entity;
import org.glydar.api.entity.EntityChanges;
import org.glydar.api.entity.EntityData;
import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.data.DataCodec;
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

    public Packet00EntityUpdate(ByteBuf rawBuf) {
        int zlibLength = rawBuf.readInt();
        if (zlibLength < 1) {
            Glydar.getLogger().info("{0} Skipping ZlibLength {1}", getPacketType(), zlibLength);
            this.entityId = -1;
            this.changes = null;
            this.data = null;
            return;
        }

        byte[] rawData = new byte[zlibLength];
        rawBuf.readBytes(rawData);

        ByteBuf buf;
        try {
            byte[] decompressed = ZLibOperations.decompress(rawData);
            buf = Unpooled.copiedBuffer(decompressed);
            Glydar.getLogger().warning("Decompressed Data : {1}", getPacketType(), Arrays.toString(decompressed));
        }
        catch (final Exception exc) {
            Glydar.getLogger().warning(exc, "Unable to decompress entity data");
            Glydar.getLogger().warning("{0} Data : {1}", getPacketType(), Arrays.toString(rawData));
            this.entityId = -1;
            this.changes = null;
            this.data = null;
            return;
        }

        buf = buf.order(ByteOrder.LITTLE_ENDIAN);
        this.entityId = buf.readLong();
        this.changes = DataCodec.readEntityChanges(buf);
        this.data = DataCodec.readEntityData(buf, changes);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.ENTITY_UPDATE;
    }

    @Override
    public void writeTo(ByteBuf buf) {
        ByteBuf buf2 = Unpooled.buffer();
        buf2 = buf2.order(ByteOrder.LITTLE_ENDIAN);
        buf2.writeLong(entityId);
        DataCodec.writeEntityChanges(buf2, changes);
        DataCodec.writeEntityData(buf2, changes, data);
        byte[] compressedData = ZLibOperations.compress(buf2.array());
        buf.writeBytes(compressedData);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        if (entityId < 0) {
            return;
        }

        handler.handle(remote, this);
    }
}
