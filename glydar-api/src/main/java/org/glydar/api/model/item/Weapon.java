package org.glydar.api.model.item;

import org.glydar.api.model.item.ItemSubtype.WeaponSubtype;

public interface Weapon extends Item {

    WeaponSubtype getSubtype();
}
