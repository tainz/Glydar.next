package org.glydar.protocol.data;

import io.netty.buffer.ByteBuf;

import org.glydar.api.item.Item;

public class PickupAction {

    private final long id;
    private final Item item;

    public PickupAction(ByteBuf buf) {
        this.id = buf.readLong();
        this.item = DataCodec.readItem(buf);

    }

    public void writeTo(ByteBuf buf) {
        buf.writeLong(id);
        DataCodec.writeItem(buf, item);
    }
}
