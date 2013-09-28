package org.glydar.core.model.item;

public enum ItemType {

    NONE,

    CONSUMABLE,

    FORMULA,

    WEAPON,

    CHEST,

    GLOVES,

    BOOT,

    SHOULDERS,

    AMULET,

    RING,

    CUBE,

    ITEM,

    COIN,

    PLATINUM_COIN,

    LEFTOVERS,

    BEAK,

    PAINTING,

    VASE,

    CANDLE,

    PET,

    PET_FOOD,

    QUEST_ITEM,

    UNKNOWN22,

    SPECIAL,

    LAMP,

    UNSUPPORTED;

    public byte getId() {
        return (byte) ordinal();
    }

    public static ItemType getById(byte id) {
        try {
            return values()[id];
        }
        catch (IndexOutOfBoundsException exc) {
            return ItemType.UNSUPPORTED;
        }
    }
}
