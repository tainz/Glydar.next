package org.glydar.core.model.item;

import org.glydar.core.model.item.ItemSubtype.WeaponSubtype;

public class Weapon extends Item {
	
	public Weapon(){
		super(ItemType.WEAPON, WeaponSubtype.SWORD);
	}
	
	public Weapon (Weapon item){
		super(item);
	}
	
	public Weapon(WeaponSubtype sub){
		super(ItemType.WEAPON, sub);
	}
	
	public WeaponSubtype getSubtype() {
        return WeaponSubtype.getById(subtypeId);
    }
}
