package org.glydar.core.model.item;

import org.glydar.api.model.item.ItemSubtype.WeaponSubtype;
import org.glydar.api.model.item.ItemType;
import org.glydar.api.model.item.Weapon;

public class CoreWeapon extends CoreItem implements Weapon {

    public CoreWeapon() {
        super(getItemTypeId(ItemType.WEAPON), getWeaponSubtypeId(WeaponSubtype.SWORD));
    }

    public CoreWeapon(byte subtypeId) {
        super(getItemTypeId(ItemType.WEAPON), subtypeId);
    }

    @Override
    public WeaponSubtype getSubtype() {
        return getWeaponSubtype(subtypeId);
    }

    public static byte getWeaponSubtypeId(WeaponSubtype subtype) {
        return (byte) subtype.ordinal();
    }

    public static WeaponSubtype getWeaponSubtype(byte id) {
        try {
            return WeaponSubtype.values()[id];
        }
        catch (IndexOutOfBoundsException exc) {
            return WeaponSubtype.UNSUPPORTED;
        }
    }
}
