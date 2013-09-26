package org.glydar.api.item;

public class Item {

    private final byte    typeId;
    private final byte    subtypeId;
    private long          modifier;     // Uint
    private long          minusModifier; // Uint
    private byte          rarity;
    private byte          materialId;
    private byte          flags;
    private short         level;        // ushort
    private ItemUpgrade[] upgrades;
    private long          upgradeCount; // unsigned

    public Item(Item i) {
        this.typeId = i.typeId;
        this.subtypeId = i.subtypeId;
        this.modifier = i.modifier;
        this.minusModifier = i.minusModifier;
        this.rarity = i.rarity;
        this.materialId = i.materialId;
        this.flags = i.flags;
        this.level = i.level;
        this.upgrades = new ItemUpgrade[i.upgrades.length];
        for (int j = 0; j < i.upgrades.length; j++) {
            this.upgrades[j] = new ItemUpgrade(i.upgrades[j]);
        }
        this.upgradeCount = i.upgradeCount;
    }

    public Item() {
        this(ItemType.CRASH, (byte) 0);
    }

    public Item(byte typeId, byte subtypeId) {
        this.typeId = typeId;
        this.subtypeId = subtypeId;

        upgrades = new ItemUpgrade[32];
        for (int i = 0; i < 32; i++) {
            upgrades[i] = new ItemUpgrade();
        }
    }

    public Item(ItemType type, byte subtype) {
        this(type.getId(), subtype);
    }

    public byte getTypeId() {
        return typeId;
    }

    public ItemType getType() {
        return ItemType.getById(typeId);
    }

    public byte getSubtypeId() {
        return subtypeId;
    }

    public ItemSubtype getSubtype() {
        return getType().getSubtypeById(subtypeId);
    }

    public long getModifier() {
        return modifier;
    }

    public void setModifier(long modifier) {
        this.modifier = modifier;
    }

    public long getMinusModifier() {
        return minusModifier;
    }

    public void setMinusModifier(long minusModifier) {
        this.minusModifier = minusModifier;
    }

    public byte getRarity() {
        return rarity;
    }

    public void setRarity(byte rarity) {
        this.rarity = rarity;
    }

    public byte getMaterialId() {
        return materialId;
    }

    public Material getMaterial() {
        return Material.getById(materialId);
    }

    public void setMaterialId(byte material) {
        this.materialId = material;
    }

    public void setMaterial(Material material) {
        this.materialId = material.getId();
    }

    public byte getFlags() {
        return flags;
    }

    public void setFlags(byte flags) {
        this.flags = flags;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public ItemUpgrade[] getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(ItemUpgrade[] upgrades) {
        this.upgrades = upgrades;
    }

    public long getUpgradeCount() {
        return upgradeCount;
    }

    public void setUpgradeCount(long upgradeCount) {
        this.upgradeCount = upgradeCount;
    }
}
