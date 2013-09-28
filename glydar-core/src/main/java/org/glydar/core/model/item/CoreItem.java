package org.glydar.core.model.item;

import org.glydar.api.model.item.Item;
import org.glydar.api.model.item.ItemType;
import org.glydar.api.model.item.ItemUpgrade;
import org.glydar.api.model.item.Material;

public class CoreItem implements Item {

    private final byte    typeId;
    protected final byte  subtypeId;
    private long          modifier;     // Uint
    private long          minusModifier; // Uint
    private byte          rarity;
    private byte          materialId;
    private byte          flags;
    private short         level;        // ushort
    private ItemUpgrade[] upgrades;
    private long          upgradeCount; // unsigned

    public CoreItem() {
        this(getItemTypeId(ItemType.NONE), (byte) 0);
    }

    public CoreItem(byte typeId, byte subtypeId) {
        this.typeId = typeId;
        this.subtypeId = subtypeId;

        upgrades = new ItemUpgrade[32];
        for (int i = 0; i < 32; i++) {
            upgrades[i] = new ItemUpgrade();
        }
    }

    @Override
    public byte getTypeId() {
        return typeId;
    }

    @Override
    public ItemType getType() {
        return getItemType(typeId);
    }

    @Override
    public byte getSubtypeId() {
        return subtypeId;
    }

    @Override
    public long getModifier() {
        return modifier;
    }

    @Override
    public void setModifier(long modifier) {
        this.modifier = modifier;
    }

    @Override
    public long getMinusModifier() {
        return minusModifier;
    }

    @Override
    public void setMinusModifier(long minusModifier) {
        this.minusModifier = minusModifier;
    }

    @Override
    public byte getRarity() {
        return rarity;
    }

    @Override
    public void setRarity(byte rarity) {
        this.rarity = rarity;
    }

    @Override
    public byte getMaterialId() {
        return materialId;
    }

    @Override
    public Material getMaterial() {
        return Material.getById(materialId);
    }

    @Override
    public void setMaterialId(byte material) {
        this.materialId = material;
    }

    @Override
    public void setMaterial(Material material) {
        this.materialId = material.getId();
    }

    @Override
    public byte getFlags() {
        return flags;
    }

    @Override
    public void setFlags(byte flags) {
        this.flags = flags;
    }

    @Override
    public short getLevel() {
        return level;
    }

    @Override
    public void setLevel(short level) {
        this.level = level;
    }

    @Override
    public ItemUpgrade[] getUpgrades() {
        return upgrades;
    }

    @Override
    public void setUpgrades(ItemUpgrade[] upgrades) {
        this.upgrades = upgrades;
    }

    @Override
    public long getUpgradeCount() {
        return upgradeCount;
    }

    @Override
    public void setUpgradeCount(long upgradeCount) {
        this.upgradeCount = upgradeCount;
    }

    public static ItemType getItemType(byte id) {
        return ItemType.values()[id];
    }

    public static byte getItemTypeId(ItemType type) {
        return (byte) type.ordinal();
    }
}
