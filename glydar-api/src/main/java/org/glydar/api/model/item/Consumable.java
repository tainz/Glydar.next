package org.glydar.api.model.item;

import org.glydar.api.model.item.ItemSubtype.ConsumableSubtype;

public interface Consumable extends Item {

    ConsumableSubtype getSubtype();
}
