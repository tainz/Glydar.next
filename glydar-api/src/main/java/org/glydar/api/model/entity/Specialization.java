package org.glydar.api.model.entity;

/* Structures and data discovered by cuwo (http://github.com/matpow2) */
public enum Specialization {

    BERSERKER(EntityClass.WARRIOR),

    GUARDIAN(EntityClass.WARRIOR),

    SNIPER(EntityClass.RANGER),

    SCOUT(EntityClass.RANGER),

    FIRE(EntityClass.MAGE),

    WATER(EntityClass.MAGE),

    ASSASIN(EntityClass.ROGUE),

    NINJA(EntityClass.ROGUE),

    UNSUPPORTED(EntityClass.UNSUPPORTED);

    private final EntityClass entityClass;

    private Specialization(EntityClass entityClass) {
        this.entityClass = entityClass;
    }

    public EntityClass getEntityClass() {
        return entityClass;
    }
}
