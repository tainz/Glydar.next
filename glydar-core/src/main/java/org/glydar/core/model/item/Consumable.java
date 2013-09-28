package org.glydar.core.model.item;

import org.glydar.core.model.item.ItemSubtype.ConsumableSubtype;

public class Consumable extends Item {
	
	public Consumable(){
		super(ItemType.CONSUMABLE, ConsumableSubtype.COOKIE);
	}
	
	public Consumable (Consumable item){
		super(item);
	}
	
	public Consumable(ConsumableSubtype sub){
		super(ItemType.CONSUMABLE, sub);
	}
	
	public ConsumableSubtype getSubtype() {
        return ConsumableSubtype.getById(subtypeId);
    }
}
