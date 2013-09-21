package org.glydar.protocol.codec;

import io.netty.buffer.ByteBuf;

import java.util.List;

import org.glydar.api.geom.LongVector3;
import org.glydar.api.item.ChunkItem;
import org.glydar.api.item.ChunkItems;
import org.glydar.api.item.Item;
import org.glydar.api.item.ItemUpgrade;

public final class ItemCodec {

    private ItemCodec() {
    }

    public static Item readItem(ByteBuf buf) {
        Item i = new Item();
        i.setType(buf.readByte());
        i.setSubtype(buf.readByte());
        buf.skipBytes(2);
        i.setModifier(buf.readUnsignedInt());
        i.setMinusModifier(buf.readUnsignedInt());
        i.setRarity(buf.readByte());
        i.setMaterial(buf.readByte());
        i.setFlags(buf.readByte());
        buf.skipBytes(1);
        i.setLevel(buf.readShort());
        buf.skipBytes(2);

        ItemUpgrade[] upgrades = i.getUpgrades();
        for (int j = 0; j < upgrades.length; ++j) {
            upgrades[j] = readItemUpgrade(buf);
        }
        i.setUpgrades(upgrades);

        i.setUpgradeCount(buf.readUnsignedInt());
        return i;
    }

    public static void writeItem(ByteBuf buf, Item i) {
        buf.writeByte(i.getType());
        buf.writeByte(i.getSubtype());
        buf.writeZero(2);
        buf.writeInt((int) i.getModifier());
        buf.writeInt((int) i.getMinusModifier());
        buf.writeByte(i.getRarity());
        buf.writeByte(i.getMaterial());
        buf.writeByte(i.getFlags());
        buf.writeZero(1);
        buf.writeShort(i.getLevel());
        buf.writeZero(2);
        ItemUpgrade[] upgrades = i.getUpgrades();
        for (int j = 0; j < upgrades.length; ++j) {
            writeItemUpgrade(buf, upgrades[j]);
        }
        buf.writeInt((int) i.getUpgradeCount());
    }

    public static ItemUpgrade readItemUpgrade(ByteBuf buf) {
        ItemUpgrade u = new ItemUpgrade();
        u.setxOffset(buf.readByte());
        u.setyOffset(buf.readByte());
        u.setzOffset(buf.readByte());
        u.setMaterial(buf.readByte());
        u.setLevel(buf.readUnsignedInt());
        return u;
    }

    public static void writeItemUpgrade(ByteBuf buf, ItemUpgrade u) {
        buf.writeByte(u.getxOffset());
        buf.writeByte(u.getyOffset());
        buf.writeByte(u.getzOffset());
        buf.writeByte(u.getMaterial());
        buf.writeInt((int) u.getLevel());
    }

    public static ChunkItem readChunkItem(ByteBuf buf) {
        Item item = ItemCodec.readItem(buf);
        LongVector3 position = GeomCodec.readLongVector3(buf);

        ChunkItem chunkItem = new ChunkItem(item, position);
        chunkItem.setRotation(buf.readFloat());
        chunkItem.setScale(buf.readFloat());
        chunkItem.setUnknown1(buf.readByte());
        buf.skipBytes(3);
        chunkItem.setDropTime(buf.readUnsignedInt());
        chunkItem.setUnknown2(buf.readUnsignedInt());
        chunkItem.setUnknown3(buf.readInt());

        return chunkItem;
    }

    public static void writeChunkItem(ByteBuf buf, ChunkItem chunkItem) {
        ItemCodec.writeItem(buf, chunkItem.getItem());
        GeomCodec.writeLongVector3(buf, chunkItem.getPosition());
        buf.writeFloat(chunkItem.getRotation());
        buf.writeFloat(chunkItem.getScale());
        buf.writeByte(chunkItem.getUnknown1());
        buf.writeZero(3);
        buf.writeInt((int) chunkItem.getDropTime());
        buf.writeInt((int) chunkItem.getUnknown2());
        buf.writeInt(chunkItem.getUnknown3());
    }

    public static ChunkItems readChunkItems(ByteBuf buf) {
        int chunkX = buf.readInt();
        int chunkY = buf.readInt();

        ChunkItems chunkItems = new ChunkItems(chunkX, chunkY);
        int length = buf.readInt();
        for (int i = 0; i < length; i++) {
            chunkItems.addItem(readChunkItem(buf));
        }

        return chunkItems;
    }

    public static void writeChunkItems(ByteBuf buf, ChunkItems chunkItems) {
        buf.writeInt(chunkItems.getChunkX());
        buf.writeInt(chunkItems.getChunkY());

        List<ChunkItem> items = chunkItems.getItems();
        buf.writeInt(items.size());
        for (ChunkItem chunkItem : items) {
            writeChunkItem(buf, chunkItem);
        }
    }
}
