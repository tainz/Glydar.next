package org.glydar.protocol.codec;

import io.netty.buffer.ByteBuf;

import org.glydar.api.item.Equipment;
import org.glydar.api.item.Item;
import org.glydar.api.item.ItemType;
import org.glydar.api.item.ItemUpgrade;

public final class ItemCodec {

    private ItemCodec() {
    }

    public static Item readItem(ByteBuf buf) {
        Item i = new Item();
        i.setType(ItemType.values()[buf.readByte()]);
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
        buf.writeByte((byte) i.getType().ordinal());
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

    public static Equipment readEquipment(ByteBuf buf) {
        Equipment e = new Equipment();
        e.setUnknown1(readItem(buf));
        e.setNeck(readItem(buf));
        e.setChest(readItem(buf));
        e.setFeet(readItem(buf));
        e.setHands(readItem(buf));
        e.setShoulder(readItem(buf));
        e.setWeaponLeft(readItem(buf));
        e.setWeaponRight(readItem(buf));
        e.setRingLeft(readItem(buf));
        e.setRingRight(readItem(buf));
        e.setLight(readItem(buf));
        e.setSpecial(readItem(buf));
        e.setPet(readItem(buf));
        return e;
    }

    public static void writeEquipment(ByteBuf buf, Equipment e) {
    	writeItem(buf, e.getUnknown1());
    	writeItem(buf, e.getNeck());
    	writeItem(buf, e.getChest());
    	writeItem(buf, e.getFeet());
    	writeItem(buf, e.getHands());
    	writeItem(buf, e.getShoulder());
    	writeItem(buf, e.getWeaponLeft());
    	writeItem(buf, e.getWeaponRight());
    	writeItem(buf, e.getRingLeft());
    	writeItem(buf, e.getRingRight());
    	writeItem(buf, e.getLight());
    	writeItem(buf, e.getSpecial());
    	writeItem(buf, e.getPet());
    }
}
