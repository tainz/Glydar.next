package org.glydar.api.item;

import org.glydar.api.item.ItemSubtype.ConsumableSubtype;
import org.glydar.api.item.ItemSubtype.DefaultSubtype;
import org.glydar.api.item.ItemSubtype.WeaponSubtype;

public enum ItemType {

    NONE,

    CONSUMABLE {

        @Override
        public ConsumableSubtype getSubtypeById(byte id) {
            return ConsumableSubtype.getById(id);
        }
    },

    FORMULA,

    WEAPON {

        @Override
        public WeaponSubtype getSubtypeById(byte id) {
            return WeaponSubtype.getById(id);
        }
    },

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

    LAMP;

    public byte getId() {
        return (byte) ordinal();
    }

    public ItemSubtype getSubtypeById(byte id) {
        return DefaultSubtype.NONE;
    }

    public static ItemType getById(byte id) {
        try {
            return values()[id];
        }
        catch (IndexOutOfBoundsException exc) {
            return null;
        }
    }
}
