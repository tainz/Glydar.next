package org.glydar.protocol.data;

import io.netty.buffer.ByteBuf;

public class ChunkItems {

    private final int         chunkLocX;
    private final int         chunkLocY;
    private final ChunkItem[] items;

    public ChunkItems(ByteBuf buf) {
        this.chunkLocX = buf.readInt();
        this.chunkLocY = buf.readInt();
        int length = buf.readInt();
        this.items = new ChunkItem[length];

        for (int i = 0; i < length; i++) {
            items[i] = new ChunkItem(buf);
        }
    }

    public void writeTo(ByteBuf buf) {
        buf.writeInt(chunkLocX);
        buf.writeInt(chunkLocY);
        buf.writeInt(items.length);
        for (final ChunkItem i : items) {
            i.writeTo(buf);
        }
    }
}
