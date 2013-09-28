package org.glydar.api.model.entity;

import org.glydar.api.model.geom.FloatVector3;
import org.glydar.api.model.geom.LongVector3;
import org.glydar.api.model.geom.Orientation;
import org.glydar.api.model.item.Equipment;
import org.glydar.api.model.item.Item;

/* Structures and data discovered by mat^2 (http://github.com/matpow2) */

public interface EntityData {

    LongVector3 getPosition();

    void setPosition(LongVector3 pos);

    Orientation getOrientation();

    void setOrientation(Orientation orientation);

    FloatVector3 getVelocity();

    void setVelocity(FloatVector3 velocity);

    FloatVector3 getAcceleration();

    void setAcceleration(FloatVector3 accel);

    FloatVector3 getExtraVelocity();

    void setExtraVelocity(FloatVector3 extraVel);

    float getLookPitch();

    void setLookPitch(float lookPitch);

    long getPhysicsFlags();

    void setPhysicsFlags(long physicsFlags);

    byte getHostileType();

    void setHostileType(byte hostileType);

    long getEntityType();

    void setEntityType(long entityType);

    byte getCurrentMode();

    void setCurrentMode(byte currentMode);

    long getLastShootTime();

    void setLastShootTime(long lastShootTime);

    long getHitCounter();

    void setHitCounter(long hitCounter);

    long getLastHitTime();

    void setLastHitTime(long lastHitTime);

    Appearance getAppearance();

    void setAppearance(Appearance app);

    byte getFlags1();

    void setFlags1(byte flags1);

    byte getFlags2();

    void setFlags2(byte flags2);

    long getRollTime();

    void setRollTime(long rollTime);;

    int getStunTime();

    void setStunTime(int stunTime);

    long getSlowedTime();

    void setSlowedTime(long slowedTime);

    long getMakeBlueTime();

    void setMakeBlueTime(long makeBlueTime);

    long getSpeedUpTime();

    void setSpeedUpTime(long speedUpTime);

    float getSlowPatchTime();

    void setSlowPatchTime(float slowPatchTime);

    EntityClass getEntityClass();

    void setEntityClass(EntityClass clazz);

    Specialization getSpecialization();

    void setSpecialization(Specialization specialization);

    float getChargedMP();

    void setChargedMP(float chargedMP);

    FloatVector3 getRayHit();

    void setRayHit(FloatVector3 rayHit);

    float getHp();

    void setHp(float hP);

    float getMp();

    void setMp(float mP);

    float getBlockPower();

    void setBlockPower(float blockPower);

    float getMaxHPMultiplier();

    void setMaxHPMultiplier(float maxHPMultiplier);

    float getShootSpeed();

    void setShootSpeed(float shootSpeed);

    float getDamageMultiplier();

    void setDamageMultiplier(float damageMultiplier);

    float getArmorMultiplier();

    void setArmorMultiplier(float armorMultiplier);

    float getResistanceMultiplier();

    void setResistanceMultiplier(float resistanceMultiplier);

    long getLevel();

    void setLevel(long level);

    long getCurrentXP();

    void setCurrentXP(long currentXP);

    Item getQuickItem();

    void setQuickItem(Item itemData);

    Equipment getEquipment();

    void setEquipment(Equipment equipment);

    long getIceBlockFour();

    void setIceBlockFour(long iceBlockFour);

    long[] getSkills();

    void setSkills(long[] skills);

    String getName();

    void setName(String name);

    long getNa1();

    void setNa1(long na1);

    long getNa2();

    void setNa2(long na2);

    byte getNa3();

    void setNa3(byte na3);

    long getNa4();

    void setNa4(long na4);

    long getNa5();

    void setNa5(long na5);

    long getNu1();

    void setNu1(long nu1);

    long getNu2();

    void setNu2(long nu2);

    long getNu3();

    void setNu3(long nu3);

    long getNu4();

    void setNu4(long nu4);

    long getNu5();

    void setNu5(long nu5);

    long getNu6();

    void setNu6(long nu6);

    byte getNu7();

    void setNu7(byte nu7);

    byte getNu8();

    void setNu8(byte nu8);

    long getParentOwner();

    void setParentOwner(long parentOwner);

    long getNu11();

    void setNu11(long nu11);

    long getNu12();

    void setNu12(long nu12);

    LongVector3 getSpawnPosition();

    void setSpawnPosition(LongVector3 spawnPosition);

    long getNu20();

    void setNu20(long nu20);

    long getNu21();

    void setNu21(long nu21);

    long getNu22();

    void setNu22(long nu22);

    byte getNu19();

    void setNu19(byte nu19);
}
