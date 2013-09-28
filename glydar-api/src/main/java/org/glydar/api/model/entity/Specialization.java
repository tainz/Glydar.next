package org.glydar.api.model.entity;

public enum Specialization {

    BERSERKER,

    GUARDIAN,

    SNIPER,

    SCOUT,

    FIRE,

    WATER,

    ASSASIN,

    NINJA;

    public static final int PER_ENTITY_CLASS = 2;

    public byte getId() {
        return (byte) ((ordinal()) % PER_ENTITY_CLASS);
    }

    public EntityClass getEntityClass() {
        return EntityClass.getById((byte) (ordinal() / PER_ENTITY_CLASS + 1));
    }

    public static Specialization getById(EntityClass clazz, byte id) {
        return Specialization.values()[clazz.ordinal() * PER_ENTITY_CLASS + id];
    }
}
