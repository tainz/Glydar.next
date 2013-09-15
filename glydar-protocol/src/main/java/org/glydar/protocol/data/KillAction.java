package org.glydar.protocol.data;

import io.netty.buffer.ByteBuf;

public class KillAction {

    private final long id;
    private final long targetId;
    private final int  xp;

    public KillAction(ByteBuf buf) {
        this.id = buf.readLong();
        this.targetId = buf.readLong();
        buf.skipBytes(4);
        this.xp = buf.readInt();
    }

    public void writeTo(ByteBuf buf) {
        buf.writeLong(id);
        buf.writeLong(targetId);
        buf.writeZero(4);
        buf.writeInt(xp);
    }
}
