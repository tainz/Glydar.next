package org.glydar.protocol.data;

import io.netty.buffer.ByteBuf;

public class DamageAction {

    private final long  id;
    private final long  targetId;
    private final float damage;

    public DamageAction(ByteBuf buf) {
        this.id = buf.readLong();
        this.targetId = buf.readLong();
        this.damage = buf.readFloat();
        buf.skipBytes(4);

    }

    public void writeTo(ByteBuf buf) {
        buf.writeLong(id);
        buf.writeLong(targetId);
        buf.writeFloat(damage);
        buf.writeZero(4);
    }
}
