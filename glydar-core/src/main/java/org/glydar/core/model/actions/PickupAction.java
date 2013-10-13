package org.glydar.core.model.actions;

import org.glydar.core.model.item.CoreItem;

public class PickupAction {

    private final long entityId;
    private CoreItem item;

    public PickupAction(long entityId, CoreItem item) {
        this.entityId = entityId;
        this.item = item;
    }

    public long getEntityId() {
        return entityId;
    }

    public CoreItem getItem() {
        return item;
    }

    public void setItem(CoreItem item) {
        this.item = item;
    }
}
