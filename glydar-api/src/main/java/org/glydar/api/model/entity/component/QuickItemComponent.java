package org.glydar.api.model.entity.component;

import org.glydar.api.model.item.Consumable;

public class QuickItemComponent implements Component {

    private Consumable consumable;

    public QuickItemComponent(Consumable consumable) {
        this.consumable = consumable;
    }

    public Consumable getConsumable() {
        return consumable;
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }
}
