package org.glydar.core.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.core.model.entity.CoreEntity;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;

import com.google.common.base.Preconditions;

public class Packet16Join implements Packet {

    private final long   id;
    private final byte[] data;

    // private final EntityData data;

    public Packet16Join(CoreEntity entity) {
        this.id = entity.getId();
        this.data = new byte[4456];
        // this.data = entity.getData();
    }

    public Packet16Join(ByteBuf buf) {
        Preconditions.checkState(buf.readInt() == 0);
        this.id = buf.readLong();
        this.data = new byte[4456];
        buf.readBytes(data);
        // this.data = DataCodec.readEntityData(buf, EntityChanges.all());
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.JOIN;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        buf.writeInt(0);
        buf.writeLong(id);
        buf.writeBytes(data);
        // DataCodec.writeEntityData(buf, EntityChanges.all(), data);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }

    public long getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

}
