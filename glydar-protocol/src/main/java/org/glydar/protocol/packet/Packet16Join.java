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

import com.google.common.base.Preconditions;

public class Packet16Join implements Packet {

    private final long       id;
    private final EntityData data;

    public Packet16Join(Entity entity) {
        this.id = entity.getId();
        this.data = entity.getData();
    }

    public Packet16Join(ByteBuf buf) {
        Preconditions.checkState(buf.readInt() == 0);
        this.id = buf.readLong();
        this.data = DataCodec.readEntityData(buf, EntityChanges.all());
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.JOIN;
    }

    @Override
    public void writeTo(ByteBuf buf) {
        buf.writeInt(0);
        buf.writeLong(id);
        DataCodec.writeEntityData(buf, EntityChanges.all(), data);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
