package org.glydar.protocol.data;

import io.netty.buffer.ByteBuf;

import org.glydar.api.geom.LongVector3;
import org.glydar.api.item.Item;
import org.glydar.protocol.codec.ItemCodec;
import org.glydar.protocol.codec.GeomCodec;

public class ChunkItem {

    private final Item        item;
    private final LongVector3 position;
    private final float       rotation;
    private final float       scale;
    private final byte        unknown1;
    private final long        dropTime;
    private final long        unknown2;
    private final int         unknown3;

    public ChunkItem(Item item, LongVector3 position) {
        this.item = item;
        this.position = position;
        this.rotation = 0;
        this.scale = 0;
        this.unknown1 = 0;
        this.dropTime = 0;
        this.unknown2 = 0;
        this.unknown3 = 0;
    }

    public ChunkItem(ByteBuf buf) {
        this.item = ItemCodec.readItem(buf);
        this.position = GeomCodec.readLongVector3(buf);
        this.rotation = buf.readFloat();
        this.scale = buf.readFloat();
        this.unknown1 = buf.readByte();
        buf.skipBytes(3);
        this.dropTime = buf.readUnsignedInt();
        this.unknown2 = buf.readUnsignedInt();
        this.unknown3 = buf.readInt();
    }

    public void writeTo(ByteBuf buf) {
        ItemCodec.writeItem(buf, item);
        GeomCodec.writeLongVector3(buf, position);
        buf.writeFloat(rotation);
        buf.writeFloat(scale);
        buf.writeByte(unknown1);
        buf.writeZero(3);
        buf.writeInt((int) dropTime);
        buf.writeInt((int) unknown2);
        buf.writeInt(unknown3);
    }
}
