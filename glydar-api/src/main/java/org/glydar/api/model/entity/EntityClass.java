package org.glydar.api.model.entity;

public enum EntityClass {

    WARRIOR,

    RANGER,

    MAGE,

    ROGUE;

    public byte getId() {
        return (byte) (ordinal() + 1);
    }

    public Specialization[] getSpecializations() {
        Specialization[] specializations = new Specialization[Specialization.PER_ENTITY_CLASS];
        for (byte i = 0; i < specializations.length; i++) {
            specializations[i] = Specialization.getById(this, i);
        }

        return specializations;
    }

    public static EntityClass getById(byte id) {
        try {
            return values()[id - 1];
        }
        catch (IndexOutOfBoundsException exc) {
            return null;
        }
    }
}
