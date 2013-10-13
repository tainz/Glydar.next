package org.glydar.api.model.entity.component;

import org.glydar.api.model.entity.EntityClass;
import org.glydar.api.model.entity.Specialization;

public class ClassComponent {

    private EntityClass entityClass;
    private Specialization specialization;
    private long[] skills;

    public ClassComponent(EntityClass entityClass, Specialization specialization, long[] skills) {
        this.entityClass = entityClass;
        this.specialization = specialization;
        this.skills = skills;
    }

    public EntityClass getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(EntityClass entityClass) {
        this.entityClass = entityClass;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public long[] getSkills() {
        return skills;
    }

    public void setSkills(long[] skills) {
        this.skills = skills;
    }
}
