package org.glydar.protocol.data;

import io.netty.buffer.ByteBuf;

public class ChunkItems {

    private final int   chunkLocX;
    private final int   chunkLocY;
    private ChunkItem[] items;

    public ChunkItems(ByteBuf buf) {
        chunkLocX = buf.readInt();
        chunkLocY = buf.readInt();

        int length = buf.readInt();
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
