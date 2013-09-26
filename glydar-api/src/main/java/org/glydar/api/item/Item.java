package org.glydar.api.item;

public class Item {

    private final byte    type;
    private final byte    subtype;
    private long          modifier;     // Uint
    private long          minusModifier; // Uint
    private byte          rarity;
    private byte          material;
    private byte          flags;
    private short         level;        // ushort
    private ItemUpgrade[] upgrades;
    private long          upgradeCount; // unsigned

    public Item(Item i) {
        this.type = (byte) i.getType().ordinal();
        this.subtype = i.getSubtype();
        this.modifier = i.getModifier();
        this.minusModifier = i.getMinusModifier();
        this.rarity = i.getRarity();
        this.material = i.getMaterial();
        this.flags = i.getFlags();
        this.level = i.getLevel();
        this.upgrades = new ItemUpgrade[i.getUpgrades().length];
        for (int j = 0; j < i.getUpgrades().length; j++) {
            this.upgrades[j] = new ItemUpgrade(i.getUpgrades()[j]);
        }
        this.upgradeCount = i.getUpgradeCount();
    }

    public Item() {
        this(ItemType.CRASH, (byte) 0);
    }

    public Item(ItemType type, byte subtype) {
        this.type = (byte) type.ordinal();
        this.subtype = subtype;

        upgrades = new ItemUpgrade[32];
        for (int i = 0; i < 32; i++) {
            upgrades[i] = new ItemUpgrade();
        }
    }

    public ItemType getType() {
        return ItemType.values()[type];
    }

    public byte getSubtype() {
        return subtype;
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

    public byte getMaterial() {
        return material;
    }

    public void setMaterial(byte material) {
        this.material = material;
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
