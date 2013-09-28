package org.glydar.api.model.entity;

import org.glydar.api.model.geom.FloatVector3;

public interface Appearance {

    byte getNotUsed1();

    void setNotUsed1(byte notUsed1);

    byte getNotUsed2();

    void setNotUsed2(byte notUsed2);

    byte getHairR();

    void setHairR(byte hairR);

    byte getHairG();

    void setHairG(byte hairG);

    byte getHairB();

    void setHairB(byte hairB);

    byte getMovementFlags();

    void setMovementFlags(byte movementFlags);

    byte getEntityFlags();

    void setEntityFlags(byte entityFlags);

    float getScale();

    void setScale(float scale);

    float getBoundingRadius();

    void setBoundingRadius(float boundingRadius);

    float getBoundingHeight();

    void setBoundingHeight(float boundingHeight);

    int getHeadModel();

    void setHeadModel(int headModel);

    int getHairModel();

    void setHairModel(int hairModel);

    int getHandModel();

    void setHandModel(int handModel);

    int getFootModel();

    void setFootModel(int footModel);

    int getBodyModel();

    void setBodyModel(int bodyModel);

    int getBackModel();

    void setBackModel(int backModel);

    int getShoulderModel();

    void setShoulderModel(int shoulderModel);

    int getWingModel();

    void setWingModel(int wingModel);

    float getHeadScale();

    void setHeadScale(float headScale);

    float getHairScale();

    void setHairScale(float hairScale);

    float getHandScale();

    void setHandScale(float handScale);

    float getFootScale();

    void setFootScale(float footScale);

    float getBodyScale();

    void setBodyScale(float bodyScale);

    float getBackScale();

    void setBackScale(float backScale);

    float getUnknown();

    void setUnknown(float unknown);

    float getWingScale();

    void setWingScale(float wingScale);

    float getBodyPitch();

    void setBodyPitch(float bodyPitch);

    float getArmPitch();

    void setArmPitch(float armPitch);

    float getArmRoll();

    void setArmRoll(float armRoll);

    float getArmYaw();

    void setArmYaw(float armYaw);

    float getFeetPitch();

    void setFeetPitch(float feetPitch);

    float getWingPitch();

    void setWingPitch(float wingPitch);

    float getBackPitch();

    void setBackPitch(float backPitch);

    FloatVector3 getBodyOffset();

    void setBodyOffset(FloatVector3 bodyOffset);

    FloatVector3 getHeadOffset();

    void setHeadOffset(FloatVector3 headOffset);

    FloatVector3 getHandOffset();

    void setHandOffset(FloatVector3 handOffset);

    FloatVector3 getFootOffset();

    void setFootOffset(FloatVector3 footOffset);

    FloatVector3 getBackOffset();

    void setBackOffset(FloatVector3 backOffset);

    FloatVector3 getWingOffset();

    void setWingOffset(FloatVector3 wingOffset);

    float getShoulderScale();

    void setShoulderScale(float shoulderScale);

    float getWeaponScale();

    void setWeaponScale(float weaponScale);
}
