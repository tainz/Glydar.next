package org.glydar.api.model.item;

/* Structures and data discovered by cuwo (http://github.com/matpow2) */
public interface ItemSubtype {

    public enum DefaultSubtype implements ItemSubtype {

        NONE;
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
    }
}
