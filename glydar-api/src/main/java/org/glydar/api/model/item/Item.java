package org.glydar.api.model.item;

public interface Item {

    ItemType getType();

    long getModifier();

    void setModifier(long modifier);

    long getMinusModifier();

    void setMinusModifier(long minusModifier);

    byte getRarity();

    void setRarity(byte rarity);

    byte getMaterialId();

    Material getMaterial();

    void setMaterialId(byte material);

    void setMaterial(Material material);

    byte getFlags();

    void setFlags(byte flags);

    short getLevel();

    void setLevel(short level);

    ItemUpgrade[] getUpgrades();

    void setUpgrades(ItemUpgrade[] upgrades);

    long getUpgradeCount();

    void setUpgradeCount(long upgradeCount);
}
