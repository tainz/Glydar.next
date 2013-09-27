package org.glydar.api.item;

public interface ItemSubtype {

    byte getId();

    public enum DefaultSubtype implements ItemSubtype {

        NONE;

        @Override
        public byte getId() {
            return 0;
        }

        public static DefaultSubtype getById(byte id) {
            return (id == 0) ? NONE : null;
        }
    }

    public enum ConsumableSubtype implements ItemSubtype {

        COOKIE,

        LIFE_POTION,

        CACTUS_POTION,

        MANA_POTION,

        GINSENG_SOUP,

        SNOWBERRY_MASH,

        MUSHROOM_SPIT,

        BOMB,

        PINEAPPLE_SLICE,

        PUMPKIN_MUFFIN,

        MUSHROOM,
        
        UNSUPPORTED;

        @Override
        public byte getId() {
            return (byte) ordinal();
        }

        public static ConsumableSubtype getById(byte id) {
            try {
                return values()[id];
            }
            catch (IndexOutOfBoundsException exc) {
                return ConsumableSubtype.UNSUPPORTED;
            }
        }
    }

    public enum WeaponSubtype implements ItemSubtype {

        SWORD,

        AXE,

        MACE,

        DAGGER,

        FIST,

        LONG_SWORD,

        BOW,

        CROSSBOW,

        BOOMERANG,

        ARROW,

        STAFF,

        WAND,

        BRACELET,

        SHIELD,

        QUIVER,

        GREAT_SWORD,

        GREAT_AXE,

        GREAT_MACE,

        PITCH_FORK,

        PITCH_AXE,

        TORCH,
        
        UNSUPPORTED;

        @Override
        public byte getId() {
            return (byte) ordinal();
        }

        public static WeaponSubtype getById(byte id) {
            try {
                return values()[id];
            }
            catch (IndexOutOfBoundsException exc) {
                return WeaponSubtype.UNSUPPORTED;
            }
        }
    }
}
