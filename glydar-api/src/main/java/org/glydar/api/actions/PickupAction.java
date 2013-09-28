package org.glydar.api.actions;

import org.glydar.api.item.Item;

public class PickupAction {

    private final long entityId;
    private Item       item;

    public PickupAction(long entityId, Item item) {
        this.entityId = entityId;
        this.item = item;
    }

    public long getEntityId() {
        return entityId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
