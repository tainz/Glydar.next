package org.glydar.api.entity;

import org.glydar.api.geom.FloatVector3;
import org.glydar.api.geom.LongVector3;
import org.glydar.api.geom.Orientation;
import org.glydar.api.item.Consumable;
import org.glydar.api.item.Equipment;

/* Structures and data discovered by mat^2 (http://github.com/matpow2) */

public class EntityData {

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
    private Consumable          quickItem;
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

    public EntityData(EntityChanges changes) {
        this.changes = changes;
        this.position = new LongVector3();
        this.velocity = new FloatVector3();
        this.acceleration = new FloatVector3();
        this.extraVelocity = new FloatVector3();
        this.rayHit = new FloatVector3();
        this.appearance = new Appearance();
        this.quickItem = new Consumable();
        this.equipment = new Equipment();
        this.spawnPosition = new LongVector3();
        this.skills = new long[11];
    }

    public EntityData(EntityData e) {
        this.changes = e.getChanges();
        this.position = e.getPosition();
        this.orientation = e.getOrientation();
        this.velocity = e.getVelocity();
        this.acceleration = e.getAcceleration();
        this.extraVelocity = e.getExtraVelocity();
        this.lookPitch = e.getLookPitch();
        this.physicsFlags = e.getPhysicsFlags();
        this.hostileType = e.getHostileType();
        this.entityType = e.getEntityType();
        this.currentMode = e.getCurrentMode();
        this.lastShootTime = e.getLastShootTime();
        this.hitCounter = e.getHitCounter();
        this.lastHitTime = e.getLastHitTime();
        this.appearance = new Appearance(e.getAppearance());
        this.flags1 = e.getFlags1();
        this.flags2 = e.getFlags2();
        this.rollTime = e.getRollTime();
        this.stunTime = e.getStunTime();
        this.slowedTime = e.getSlowedTime();
        this.makeBlueTime = e.getMakeBlueTime();
        this.speedUpTime = e.getSpeedUpTime();
        this.slowPatchTime = e.getSlowPatchTime();
        this.entityClassId = e.entityClassId;
        this.specializationId = e.specializationId;
        this.chargedMP = e.getChargedMP();
        this.rayHit = e.getRayHit();
        hp = e.getHp();
        mp = e.getMp();
        this.blockPower = e.getBlockPower();
        this.maxHPMultiplier = e.getMaxHPMultiplier();
        this.shootSpeed = e.getShootSpeed();
        this.damageMultiplier = e.getDamageMultiplier();
        this.armorMultiplier = e.getArmorMultiplier();
        this.resistanceMultiplier = e.getResistanceMultiplier();
        this.level = e.getLevel();
        this.currentXP = e.getCurrentXP();
        this.quickItem = new Consumable(e.getQuickItem());
        this.equipment = new Equipment(e.getEquipment());
        this.iceBlockFour = e.getIceBlockFour();
        this.skills = e.getSkills();
        this.name = e.getName();
        this.na1 = e.getNa1();
        this.na2 = e.getNa2();
        this.na3 = e.getNa3();
        this.na4 = e.getNa4();
        this.na5 = e.getNa5();
        this.nu1 = e.getNu1();
        this.nu2 = e.getNu2();
        this.nu3 = e.getNu3();
        this.nu4 = e.getNu4();
        this.nu5 = e.getNu5();
        this.nu6 = e.getNu6();
        this.nu7 = e.getNu7();
        this.nu8 = e.getNu8();
        this.parentOwner = e.getParentOwner();
        this.nu11 = e.getNu11();
        this.nu12 = e.getNu12();
        this.spawnPosition = e.getSpawnPosition();
        this.nu20 = e.getNu20();
        this.nu21 = e.getNu21();
        this.nu22 = e.getNu22();
        this.nu19 = e.getNu19();
    }

    public EntityChanges getChanges() {
        return changes;
    }

    public LongVector3 getPosition() {
        return position;
    }

    public void setPosition(LongVector3 pos) {
        this.position = pos;
        changes.set(EntityChange.POSITION);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        changes.set(EntityChange.ORIENTATION);
    }

    public FloatVector3 getVelocity() {
        return velocity;
    }

    public void setVelocity(FloatVector3 velocity) {
        this.velocity = velocity;
        changes.set(EntityChange.VELOCITY);
    }

    public FloatVector3 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(FloatVector3 accel) {
        this.acceleration = accel;
        changes.set(EntityChange.ACCELERATION);
    }

    public FloatVector3 getExtraVelocity() {
        return extraVelocity;
    }

    public void setExtraVelocity(FloatVector3 extraVel) {
        this.extraVelocity = extraVel;
        changes.set(EntityChange.EXTRA_VELOCITY);
    }

    public float getLookPitch() {
        return lookPitch;
    }

    public void setLookPitch(float lookPitch) {
        this.lookPitch = lookPitch;
        changes.set(EntityChange.LOOK_PITCH);
    }

    public long getPhysicsFlags() {
        return physicsFlags;
    }

    public void setPhysicsFlags(long physicsFlags) {
        this.physicsFlags = physicsFlags;
        changes.set(EntityChange.PHYSICS_FLAGS);
    }

    public byte getHostileType() {
        return hostileType;
    }

    public void setHostileType(byte hostileType) {
        this.hostileType = hostileType;
        changes.set(EntityChange.HOSTILE_TYPE);
    }

    public long getEntityType() {
        return entityType;
    }

    public void setEntityType(long entityType) {
        this.entityType = entityType;
        changes.set(EntityChange.ENTITY_TYPE);
    }

    public byte getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(byte currentMode) {
        this.currentMode = currentMode;
        changes.set(EntityChange.CURRENT_MODE);
    }

    public long getLastShootTime() {
        return lastShootTime;
    }

    public void setLastShootTime(long lastShootTime) {
        this.lastShootTime = lastShootTime;
        changes.set(EntityChange.LAST_SHOOT_TIME);
    }

    public long getHitCounter() {
        return hitCounter;
    }

    public void setHitCounter(long hitCounter) {
        this.hitCounter = hitCounter;
        changes.set(EntityChange.HIT_COUNTER);
    }

    public long getLastHitTime() {
        return lastHitTime;
    }

    public void setLastHitTime(long lastHitTime) {
        this.lastHitTime = lastHitTime;
        changes.set(EntityChange.LAST_HIT_TIME);
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance app) {
        this.appearance = app;
        changes.set(EntityChange.APPEARANCE);
    }

    public byte getFlags1() {
        return flags1;
    }

    public void setFlags1(byte flags1) {
        this.flags1 = flags1;
        changes.set(EntityChange.FLAGS);
    }

    public byte getFlags2() {
        return flags2;
    }

    public void setFlags2(byte flags2) {
        this.flags2 = flags2;
        changes.set(EntityChange.FLAGS);
    }

    public long getRollTime() {
        return rollTime;
    }

    public void setRollTime(long rollTime) {
        this.rollTime = rollTime;
        changes.set(EntityChange.ROLL_TIME);
    }

    public int getStunTime() {
        return stunTime;
    }

    public void setStunTime(int stunTime) {
        this.stunTime = stunTime;
        changes.set(EntityChange.STUN_TIME);
    }

    public long getSlowedTime() {
        return slowedTime;
    }

    public void setSlowedTime(long slowedTime) {
        this.slowedTime = slowedTime;
        changes.set(EntityChange.SLOWED_TIME);
    }

    public long getMakeBlueTime() {
        return makeBlueTime;
    }

    public void setMakeBlueTime(long makeBlueTime) {
        this.makeBlueTime = makeBlueTime;
        changes.set(EntityChange.MAKE_BLUE_TIME);
    }

    public long getSpeedUpTime() {
        return speedUpTime;
    }

    public void setSpeedUpTime(long speedUpTime) {
        this.speedUpTime = speedUpTime;
        changes.set(EntityChange.SPEED_UP_TIME);
    }

    public float getSlowPatchTime() {
        return slowPatchTime;
    }

    public void setSlowPatchTime(float slowPatchTime) {
        this.slowPatchTime = slowPatchTime;
        changes.set(EntityChange.SLOW_PATCH_TIME);
    }

    public EntityClass getEntityClass() {
        return EntityClass.getById(entityClassId);
    }

    public byte getEntityClassId() {
        return entityClassId;
    }

    public void setEntityClass(EntityClass clazz) {
        this.entityClassId = clazz.getId();
    }

    public void setEntityClassId(byte clazzId) {
        this.entityClassId = clazzId;
        changes.set(EntityChange.ENTITY_CLASS);
    }

    public Specialization getSpecialization() {
        return Specialization.getById(getEntityClass(), specializationId);
    }

    public byte getSpecializationId() {
        return specializationId;
    }

    public void setSpecialization(Specialization specialization) {
        this.specializationId = specialization.getId();
    }

    public void setSpecializationId(byte specializationId) {
        this.specializationId = specializationId;
        changes.set(EntityChange.SPECIALIZATION);
    }

    public float getChargedMP() {
        return chargedMP;
    }

    public void setChargedMP(float chargedMP) {
        this.chargedMP = chargedMP;
        changes.set(EntityChange.CHARGED_MP);
    }

    public FloatVector3 getRayHit() {
        return rayHit;
    }

    public void setRayHit(FloatVector3 rayHit) {
        this.rayHit = rayHit;
        changes.set(EntityChange.RAY_HIT);
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hP) {
        hp = hP;
        changes.set(EntityChange.HP);
    }

    public float getMp() {
        return mp;
    }

    public void setMp(float mP) {
        mp = mP;
        changes.set(EntityChange.MP);
    }

    public float getBlockPower() {
        return blockPower;
    }

    public void setBlockPower(float blockPower) {
        this.blockPower = blockPower;
        changes.set(EntityChange.BLOCK_POWER);
    }

    public float getMaxHPMultiplier() {
        return maxHPMultiplier;
    }

    public void setMaxHPMultiplier(float maxHPMultiplier) {
        this.maxHPMultiplier = maxHPMultiplier;
        changes.set(EntityChange.MULTIPLIERS);
    }

    public float getShootSpeed() {
        return shootSpeed;
    }

    public void setShootSpeed(float shootSpeed) {
        this.shootSpeed = shootSpeed;
        changes.set(EntityChange.MULTIPLIERS);
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
        changes.set(EntityChange.MULTIPLIERS);
    }

    public float getArmorMultiplier() {
        return armorMultiplier;
    }

    public void setArmorMultiplier(float armorMultiplier) {
        this.armorMultiplier = armorMultiplier;
        changes.set(EntityChange.MULTIPLIERS);
    }

    public float getResistanceMultiplier() {
        return resistanceMultiplier;
    }

    public void setResistanceMultiplier(float resistanceMultiplier) {
        this.resistanceMultiplier = resistanceMultiplier;
        changes.set(EntityChange.MULTIPLIERS);
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
        changes.set(EntityChange.LEVEL);
    }

    public long getCurrentXP() {
        return currentXP;
    }

    public void setCurrentXP(long currentXP) {
        this.currentXP = currentXP;
        changes.set(EntityChange.CURRENT_XP);
    }

    public Consumable getQuickItem() {
        return quickItem;
    }

    public void setQuickItem(Consumable itemData) {
        this.quickItem = itemData;
        changes.set(EntityChange.QUICK_ITEM);
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;

    }

    public long getIceBlockFour() {
        return iceBlockFour;
    }

    public void setIceBlockFour(long iceBlockFour) {
        this.iceBlockFour = iceBlockFour;
        changes.set(EntityChange.ICE_BLOCK_FOUR);
    }

    public long[] getSkills() {
        return skills;
    }

    public void setSkills(long[] skills) {
        this.skills = skills;
        changes.set(EntityChange.SKILLS);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        changes.set(EntityChange.NAME);
    }

    public long getNa1() {
        return na1;
    }

    public void setNa1(long na1) {
        this.na1 = na1;
        changes.set(EntityChange.NA_1_2);
    }

    public long getNa2() {
        return na2;
    }

    public void setNa2(long na2) {
        this.na2 = na2;
        changes.set(EntityChange.NA_1_2);
    }

    public byte getNa3() {
        return na3;
    }

    public void setNa3(byte na3) {
        this.na3 = na3;
        changes.set(EntityChange.NA_3);
    }

    public long getNa4() {
        return na4;
    }

    public void setNa4(long na4) {
        this.na4 = na4;
        changes.set(EntityChange.NA_4);
    }

    public long getNa5() {
        return na5;
    }

    public void setNa5(long na5) {
        this.na5 = na5;
        changes.set(EntityChange.NA_5_NU_11_12);
    }

    public long getNu1() {
        return nu1;
    }

    public void setNu1(long nu1) {
        this.nu1 = nu1;
        changes.set(EntityChange.NU_1_2_3);
    }

    public long getNu2() {
        return nu2;
    }

    public void setNu2(long nu2) {
        this.nu2 = nu2;
        changes.set(EntityChange.NU_1_2_3);
    }

    public long getNu3() {
        return nu3;
    }

    public void setNu3(long nu3) {
        this.nu3 = nu3;
        changes.set(EntityChange.NU_1_2_3);
    }

    public long getNu4() {
        return nu4;
    }

    public void setNu4(long nu4) {
        this.nu4 = nu4;
        changes.set(EntityChange.NU_4_5_6);
    }

    public long getNu5() {
        return nu5;
    }

    public void setNu5(long nu5) {
        this.nu5 = nu5;
        changes.set(EntityChange.NU_4_5_6);
    }

    public long getNu6() {
        return nu6;
    }

    public void setNu6(long nu6) {
        this.nu6 = nu6;
        changes.set(EntityChange.NU_4_5_6);
    }

    public byte getNu7() {
        return nu7;
    }

    public void setNu7(byte nu7) {
        this.nu7 = nu7;
        changes.set(EntityChange.NU_7);
    }

    public byte getNu8() {
        return nu8;
    }

    public void setNu8(byte nu8) {
        this.nu8 = nu8;
        changes.set(EntityChange.NU_8);
    }

    public long getParentOwner() {
        return parentOwner;
    }

    public void setParentOwner(long parentOwner) {
        this.parentOwner = parentOwner;
        changes.set(EntityChange.PARENT_OWNER);
    }

    public long getNu11() {
        return nu11;
    }

    public void setNu11(long nu11) {
        this.nu11 = nu11;
        changes.set(EntityChange.NA_5_NU_11_12);
    }

    public long getNu12() {
        return nu12;
    }

    public void setNu12(long nu12) {
        this.nu12 = nu12;
        changes.set(EntityChange.NA_5_NU_11_12);
    }

    public LongVector3 getSpawnPosition() {
        return spawnPosition;
    }

    public void setSpawnPosition(LongVector3 spawnPosition) {
        this.spawnPosition = spawnPosition;
        changes.set(EntityChange.SPAWN_POSITION);
    }

    public long getNu20() {
        return nu20;
    }

    public void setNu20(long nu20) {
        this.nu20 = nu20;
        changes.set(EntityChange.NU_20_21_22);
    }

    public long getNu21() {
        return nu21;
    }

    public void setNu21(long nu21) {
        this.nu21 = nu21;
        changes.set(EntityChange.NU_20_21_22);
    }

    public long getNu22() {
        return nu22;
    }

    public void setNu22(long nu22) {
        this.nu22 = nu22;
        changes.set(EntityChange.NU_20_21_22);
    }

    public byte getNu19() {
        return nu19;
    }

    public void setNu19(byte nu19) {
        this.nu19 = nu19;
        changes.set(EntityChange.NU_19);
    }
}
