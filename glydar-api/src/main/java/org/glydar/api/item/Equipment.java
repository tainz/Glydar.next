package org.glydar.api.item;

public class Equipment {

    private final Item[] items;

    public Equipment() {
        this.items = new Item[EquipmentSlot.values().length];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item();
        }
    }

    public Equipment(Equipment other) {
        this.items = new Item[other.items.length];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(other.items[i]);
        }
    }

    public Item get(EquipmentSlot slot) {
        return items[slot.ordinal()];
    }

    public void set(EquipmentSlot slot, Item item) {
        items[slot.ordinal()] = item;
    }
}
