package org.glydar.api.model.entity;

/* Structures and data discovered by cuwo (http://github.com/matpow2) */
public enum EntityClass {

    WARRIOR(Specialization.BERSERKER, Specialization.GUARDIAN),

    RANGER(Specialization.SNIPER, Specialization.SCOUT),

    MAGE(Specialization.FIRE, Specialization.WATER),

    ROGUE(Specialization.ASSASIN, Specialization.NINJA),

    UNSUPPORTED;

    private final Specialization[] specializations;

    private EntityClass(Specialization... specializations) {
        this.specializations = specializations;
    }

    public Specialization[] getSpecializations() {
        return specializations;
    }
}
