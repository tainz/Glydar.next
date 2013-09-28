package org.glydar.core.model.entity;

import org.glydar.api.model.entity.Appearance;
import org.glydar.api.model.entity.EntityClass;
import org.glydar.api.model.entity.EntityData;
import org.glydar.api.model.entity.Specialization;
import org.glydar.api.model.geom.FloatVector3;
import org.glydar.api.model.geom.LongVector3;
import org.glydar.api.model.geom.Orientation;
import org.glydar.api.model.item.Equipment;
import org.glydar.api.model.item.Item;
import org.glydar.core.model.item.CoreConsumable;
import org.glydar.core.model.item.CoreEquipment;

public class CoreEntityData implements EntityData {

    private final EntityChanges changes;

    private LongVector3         position;
    private Orientation         orientation;
    private FloatVector3        velocity;
    private FloatVector3        acceleration;
    private FloatVector3        extraVelocity;

    private float               lookPitch;
    private long                physicsFlags;        // Uint
    private byte                hostileType;
    private long                entityType;          // Uint
    private byte                currentMode;
    private long                lastShootTime;       // Uint
    private long                hitCounter;          // Uint
    private long                lastHitTime;         // Uint
    private Appearance          appearance;
    private byte                flags1;
    private byte                flags2;
    private long                rollTime;            // Uint
    private int                 stunTime;
    private long                slowedTime;          // Uint
    private long                makeBlueTime;        // Uint
    private long                speedUpTime;         // Uint
    private float               slowPatchTime;
    private byte                entityClassId;
    private byte                specializationId;
    private float               chargedMP;

    private FloatVector3        rayHit;

    private float               hp;
    private float               mp;

    private float               blockPower;
    private float               maxHPMultiplier;
    private float               shootSpeed;
    private float               damageMultiplier;
    private float               armorMultiplier;
    private float               resistanceMultiplier;
    private long                level;               // Uint
    private long                currentXP;           // Uint
    private Item                quickItem;
    private Equipment           equipment;

    private long                iceBlockFour;        // Uint
    private long[]              skills;
    private String              name;

    private long                na1;                 // Uint
    private long                na2;                 // |
    private byte                na3;
    private long                na4;
    private long                na5;
    private long                nu1;
    private long                nu2;
    private long                nu3;
    private long                nu4;
    private long                nu5;
    private long                nu6;
    private byte                nu7;
    private byte                nu8;
    private long                parentOwner;
    private long                nu11;
    private long                nu12;
    private LongVector3         spawnPosition;
    private long                nu20;
    private long                nu21;
    private long                nu22;
    private byte                nu19;

    public CoreEntityData(EntityChanges changes) {
        this.changes = changes;
        this.position = new LongVector3();
        this.velocity = new FloatVector3();
        this.acceleration = new FloatVector3();
        this.extraVelocity = new FloatVector3();
        this.rayHit = new FloatVector3();
        this.appearance = new CoreAppearance();
        this.quickItem = new CoreConsumable();
        this.equipment = new CoreEquipment();
        this.spawnPosition = new LongVector3();
        this.skills = new long[11];
    }

    public EntityChanges getChanges() {
        return changes;
    }

    @Override
    public LongVector3 getPosition() {
        return position;
    }

    @Override
    public void setPosition(LongVector3 pos) {
        this.position = pos;
        changes.set(EntityChange.POSITION);
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        changes.set(EntityChange.ORIENTATION);
    }

    @Override
    public FloatVector3 getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(FloatVector3 velocity) {
        this.velocity = velocity;
        changes.set(EntityChange.VELOCITY);
    }

    @Override
    public FloatVector3 getAcceleration() {
        return acceleration;
    }

    @Override
    public void setAcceleration(FloatVector3 accel) {
        this.acceleration = accel;
        changes.set(EntityChange.ACCELERATION);
    }

    @Override
    public FloatVector3 getExtraVelocity() {
        return extraVelocity;
    }

    @Override
    public void setExtraVelocity(FloatVector3 extraVel) {
        this.extraVelocity = extraVel;
        changes.set(EntityChange.EXTRA_VELOCITY);
    }

    @Override
    public float getLookPitch() {
        return lookPitch;
    }

    @Override
    public void setLookPitch(float lookPitch) {
        this.lookPitch = lookPitch;
        changes.set(EntityChange.LOOK_PITCH);
    }

    @Override
    public long getPhysicsFlags() {
        return physicsFlags;
    }

    @Override
    public void setPhysicsFlags(long physicsFlags) {
        this.physicsFlags = physicsFlags;
        changes.set(EntityChange.PHYSICS_FLAGS);
    }

    @Override
    public byte getHostileType() {
        return hostileType;
    }

    @Override
    public void setHostileType(byte hostileType) {
        this.hostileType = hostileType;
        changes.set(EntityChange.HOSTILE_TYPE);
    }

    @Override
    public long getEntityType() {
        return entityType;
    }

    @Override
    public void setEntityType(long entityType) {
        this.entityType = entityType;
        changes.set(EntityChange.ENTITY_TYPE);
    }

    @Override
    public byte getCurrentMode() {
        return currentMode;
    }

    @Override
    public void setCurrentMode(byte currentMode) {
        this.currentMode = currentMode;
        changes.set(EntityChange.CURRENT_MODE);
    }

    @Override
    public long getLastShootTime() {
        return lastShootTime;
    }

    @Override
    public void setLastShootTime(long lastShootTime) {
        this.lastShootTime = lastShootTime;
        changes.set(EntityChange.LAST_SHOOT_TIME);
    }

    @Override
    public long getHitCounter() {
        return hitCounter;
    }

    @Override
    public void setHitCounter(long hitCounter) {
        this.hitCounter = hitCounter;
        changes.set(EntityChange.HIT_COUNTER);
    }

    @Override
    public long getLastHitTime() {
        return lastHitTime;
    }

    @Override
    public void setLastHitTime(long lastHitTime) {
        this.lastHitTime = lastHitTime;
        changes.set(EntityChange.LAST_HIT_TIME);
    }

    @Override
    public Appearance getAppearance() {
        return appearance;
    }

    @Override
    public void setAppearance(Appearance app) {
        this.appearance = app;
        changes.set(EntityChange.APPEARANCE);
    }

    @Override
    public byte getFlags1() {
        return flags1;
    }

    @Override
    public void setFlags1(byte flags1) {
        this.flags1 = flags1;
        changes.set(EntityChange.FLAGS);
    }

    @Override
    public byte getFlags2() {
        return flags2;
    }

    @Override
    public void setFlags2(byte flags2) {
        this.flags2 = flags2;
        changes.set(EntityChange.FLAGS);
    }

    @Override
    public long getRollTime() {
        return rollTime;
    }

    @Override
    public void setRollTime(long rollTime) {
        this.rollTime = rollTime;
        changes.set(EntityChange.ROLL_TIME);
    }

    @Override
    public int getStunTime() {
        return stunTime;
    }

    @Override
    public void setStunTime(int stunTime) {
        this.stunTime = stunTime;
        changes.set(EntityChange.STUN_TIME);
    }

    @Override
    public long getSlowedTime() {
        return slowedTime;
    }

    @Override
    public void setSlowedTime(long slowedTime) {
        this.slowedTime = slowedTime;
        changes.set(EntityChange.SLOWED_TIME);
    }

    @Override
    public long getMakeBlueTime() {
        return makeBlueTime;
    }

    @Override
    public void setMakeBlueTime(long makeBlueTime) {
        this.makeBlueTime = makeBlueTime;
        changes.set(EntityChange.MAKE_BLUE_TIME);
    }

    @Override
    public long getSpeedUpTime() {
        return speedUpTime;
    }

    @Override
    public void setSpeedUpTime(long speedUpTime) {
        this.speedUpTime = speedUpTime;
        changes.set(EntityChange.SPEED_UP_TIME);
    }

    @Override
    public float getSlowPatchTime() {
        return slowPatchTime;
    }

    @Override
    public void setSlowPatchTime(float slowPatchTime) {
        this.slowPatchTime = slowPatchTime;
        changes.set(EntityChange.SLOW_PATCH_TIME);
    }

    @Override
    public EntityClass getEntityClass() {
        return EntityClass.getById(entityClassId);
    }

    @Override
    public byte getEntityClassId() {
        return entityClassId;
    }

    @Override
    public void setEntityClass(EntityClass clazz) {
        this.entityClassId = clazz.getId();
    }

    @Override
    public void setEntityClassId(byte clazzId) {
        this.entityClassId = clazzId;
        changes.set(EntityChange.ENTITY_CLASS);
    }

    @Override
    public Specialization getSpecialization() {
        return Specialization.getById(getEntityClass(), specializationId);
    }

    @Override
    public byte getSpecializationId() {
        return specializationId;
    }

    @Override
    public void setSpecialization(Specialization specialization) {
        this.specializationId = specialization.getId();
    }

    @Override
    public void setSpecializationId(byte specializationId) {
        this.specializationId = specializationId;
        changes.set(EntityChange.SPECIALIZATION);
    }

    @Override
    public float getChargedMP() {
        return chargedMP;
    }

    @Override
    public void setChargedMP(float chargedMP) {
        this.chargedMP = chargedMP;
        changes.set(EntityChange.CHARGED_MP);
    }

    @Override
    public FloatVector3 getRayHit() {
        return rayHit;
    }

    @Override
    public void setRayHit(FloatVector3 rayHit) {
        this.rayHit = rayHit;
        changes.set(EntityChange.RAY_HIT);
    }

    @Override
    public float getHp() {
        return hp;
    }

    @Override
    public void setHp(float hP) {
        hp = hP;
        changes.set(EntityChange.HP);
    }

    @Override
    public float getMp() {
        return mp;
    }

    @Override
    public void setMp(float mP) {
        mp = mP;
        changes.set(EntityChange.MP);
    }

    @Override
    public float getBlockPower() {
        return blockPower;
    }

    @Override
    public void setBlockPower(float blockPower) {
        this.blockPower = blockPower;
        changes.set(EntityChange.BLOCK_POWER);
    }

    @Override
    public float getMaxHPMultiplier() {
        return maxHPMultiplier;
    }

    @Override
    public void setMaxHPMultiplier(float maxHPMultiplier) {
        this.maxHPMultiplier = maxHPMultiplier;
        changes.set(EntityChange.MULTIPLIERS);
    }

    @Override
    public float getShootSpeed() {
        return shootSpeed;
    }

    @Override
    public void setShootSpeed(float shootSpeed) {
        this.shootSpeed = shootSpeed;
        changes.set(EntityChange.MULTIPLIERS);
    }

    @Override
    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    @Override
    public void setDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
        changes.set(EntityChange.MULTIPLIERS);
    }

    @Override
    public float getArmorMultiplier() {
        return armorMultiplier;
    }

    @Override
    public void setArmorMultiplier(float armorMultiplier) {
        this.armorMultiplier = armorMultiplier;
        changes.set(EntityChange.MULTIPLIERS);
    }

    @Override
    public float getResistanceMultiplier() {
        return resistanceMultiplier;
    }

    @Override
    public void setResistanceMultiplier(float resistanceMultiplier) {
        this.resistanceMultiplier = resistanceMultiplier;
        changes.set(EntityChange.MULTIPLIERS);
    }

    @Override
    public long getLevel() {
        return level;
    }

    @Override
    public void setLevel(long level) {
        this.level = level;
        changes.set(EntityChange.LEVEL);
    }

    @Override
    public long getCurrentXP() {
        return currentXP;
    }

    @Override
    public void setCurrentXP(long currentXP) {
        this.currentXP = currentXP;
        changes.set(EntityChange.CURRENT_XP);
    }

    @Override
    public Item getQuickItem() {
        return quickItem;
    }

    @Override
    public void setQuickItem(Item itemData) {
        this.quickItem = itemData;
        changes.set(EntityChange.QUICK_ITEM);
    }

    @Override
    public Equipment getEquipment() {
        return equipment;
    }

    @Override
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;

    }

    @Override
    public long getIceBlockFour() {
        return iceBlockFour;
    }

    @Override
    public void setIceBlockFour(long iceBlockFour) {
        this.iceBlockFour = iceBlockFour;
        changes.set(EntityChange.ICE_BLOCK_FOUR);
    }

    @Override
    public long[] getSkills() {
        return skills;
    }

    @Override
    public void setSkills(long[] skills) {
        this.skills = skills;
        changes.set(EntityChange.SKILLS);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        changes.set(EntityChange.NAME);
    }

    @Override
    public long getNa1() {
        return na1;
    }

    @Override
    public void setNa1(long na1) {
        this.na1 = na1;
        changes.set(EntityChange.NA_1_2);
    }

    @Override
    public long getNa2() {
        return na2;
    }

    @Override
    public void setNa2(long na2) {
        this.na2 = na2;
        changes.set(EntityChange.NA_1_2);
    }

    @Override
    public byte getNa3() {
        return na3;
    }

    @Override
    public void setNa3(byte na3) {
        this.na3 = na3;
        changes.set(EntityChange.NA_3);
    }

    @Override
    public long getNa4() {
        return na4;
    }

    @Override
    public void setNa4(long na4) {
        this.na4 = na4;
        changes.set(EntityChange.NA_4);
    }

    @Override
    public long getNa5() {
        return na5;
    }

    @Override
    public void setNa5(long na5) {
        this.na5 = na5;
        changes.set(EntityChange.NA_5_NU_11_12);
    }

    @Override
    public long getNu1() {
        return nu1;
    }

    @Override
    public void setNu1(long nu1) {
        this.nu1 = nu1;
        changes.set(EntityChange.NU_1_2_3);
    }

    @Override
    public long getNu2() {
        return nu2;
    }

    @Override
    public void setNu2(long nu2) {
        this.nu2 = nu2;
        changes.set(EntityChange.NU_1_2_3);
    }

    @Override
    public long getNu3() {
        return nu3;
    }

    @Override
    public void setNu3(long nu3) {
        this.nu3 = nu3;
        changes.set(EntityChange.NU_1_2_3);
    }

    @Override
    public long getNu4() {
        return nu4;
    }

    @Override
    public void setNu4(long nu4) {
        this.nu4 = nu4;
        changes.set(EntityChange.NU_4_5_6);
    }

    @Override
    public long getNu5() {
        return nu5;
    }

    @Override
    public void setNu5(long nu5) {
        this.nu5 = nu5;
        changes.set(EntityChange.NU_4_5_6);
    }

    @Override
    public long getNu6() {
        return nu6;
    }

    @Override
    public void setNu6(long nu6) {
        this.nu6 = nu6;
        changes.set(EntityChange.NU_4_5_6);
    }

    @Override
    public byte getNu7() {
        return nu7;
    }

    @Override
    public void setNu7(byte nu7) {
        this.nu7 = nu7;
        changes.set(EntityChange.NU_7);
    }

    @Override
    public byte getNu8() {
        return nu8;
    }

    @Override
    public void setNu8(byte nu8) {
        this.nu8 = nu8;
        changes.set(EntityChange.NU_8);
    }

    @Override
    public long getParentOwner() {
        return parentOwner;
    }

    @Override
    public void setParentOwner(long parentOwner) {
        this.parentOwner = parentOwner;
        changes.set(EntityChange.PARENT_OWNER);
    }

    @Override
    public long getNu11() {
        return nu11;
    }

    @Override
    public void setNu11(long nu11) {
        this.nu11 = nu11;
        changes.set(EntityChange.NA_5_NU_11_12);
    }

    @Override
    public long getNu12() {
        return nu12;
    }

    @Override
    public void setNu12(long nu12) {
        this.nu12 = nu12;
        changes.set(EntityChange.NA_5_NU_11_12);
    }

    @Override
    public LongVector3 getSpawnPosition() {
        return spawnPosition;
    }

    @Override
    public void setSpawnPosition(LongVector3 spawnPosition) {
        this.spawnPosition = spawnPosition;
        changes.set(EntityChange.SPAWN_POSITION);
    }

    @Override
    public long getNu20() {
        return nu20;
    }

    @Override
    public void setNu20(long nu20) {
        this.nu20 = nu20;
        changes.set(EntityChange.NU_20_21_22);
    }

    @Override
    public long getNu21() {
        return nu21;
    }

    @Override
    public void setNu21(long nu21) {
        this.nu21 = nu21;
        changes.set(EntityChange.NU_20_21_22);
    }

    @Override
    public long getNu22() {
        return nu22;
    }

    @Override
    public void setNu22(long nu22) {
        this.nu22 = nu22;
        changes.set(EntityChange.NU_20_21_22);
    }

    @Override
    public byte getNu19() {
        return nu19;
    }

    @Override
    public void setNu19(byte nu19) {
        this.nu19 = nu19;
        changes.set(EntityChange.NU_19);
    }
}
