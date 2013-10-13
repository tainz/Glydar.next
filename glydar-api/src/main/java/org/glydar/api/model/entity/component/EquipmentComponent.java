package org.glydar.api.model.entity.component;

import org.glydar.api.model.item.Equipment;

public class EquipmentComponent implements Component {

    private Equipment equipment;

    public EquipmentComponent(Equipment equipment) {
        this.equipment = equipment;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipement(Equipment equipment) {
        this.equipment = equipment;
    }
}
