package org.glydar.protocol.codec;

import io.netty.buffer.ByteBuf;

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
}
