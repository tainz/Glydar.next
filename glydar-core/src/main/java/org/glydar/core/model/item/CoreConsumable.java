package org.glydar.core.model.item;

import org.glydar.api.model.item.Consumable;
import org.glydar.api.model.item.ItemSubtype.ConsumableSubtype;
import org.glydar.api.model.item.ItemType;

public class CoreConsumable extends CoreItem implements Consumable {

    public CoreConsumable() {
        super(getItemTypeId(ItemType.CONSUMABLE), getSubtypeId(ConsumableSubtype.COOKIE));
    }

    public CoreConsumable(byte subtypeId) {
        super(getItemTypeId(ItemType.CONSUMABLE), subtypeId);
    }

    @Override
    public ConsumableSubtype getSubtype() {
        return getSubtype(subtypeId);
    }

    public static byte getSubtypeId(ConsumableSubtype subtype) {
        return (byte) subtype.ordinal();
    }

    public static ConsumableSubtype getSubtype(byte id) {
        try {
            return ConsumableSubtype.values()[id];
        }
        catch (IndexOutOfBoundsException exc) {
            return ConsumableSubtype.UNSUPPORTED;
        }
    }
}
