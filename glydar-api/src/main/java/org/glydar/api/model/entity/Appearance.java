package org.glydar.api.model.entity;

import org.glydar.api.model.geom.FloatVector3;

public interface Appearance {

    public abstract byte getNotUsed1();

    public abstract void setNotUsed1(byte notUsed1);

    public abstract byte getNotUsed2();

    public abstract void setNotUsed2(byte notUsed2);

    public abstract byte getHairR();

    public abstract void setHairR(byte hairR);

    public abstract byte getHairG();

    public abstract void setHairG(byte hairG);

    public abstract byte getHairB();

    public abstract void setHairB(byte hairB);

    public abstract byte getMovementFlags();

    public abstract void setMovementFlags(byte movementFlags);

    public abstract byte getEntityFlags();

    public abstract void setEntityFlags(byte entityFlags);

    public abstract float getScale();

    public abstract void setScale(float scale);

    public abstract float getBoundingRadius();

    public abstract void setBoundingRadius(float boundingRadius);

    public abstract float getBoundingHeight();

    public abstract void setBoundingHeight(float boundingHeight);

    public abstract int getHeadModel();

    public abstract void setHeadModel(int headModel);

    public abstract int getHairModel();

    public abstract void setHairModel(int hairModel);

    public abstract int getHandModel();

    public abstract void setHandModel(int handModel);

    public abstract int getFootModel();

    public abstract void setFootModel(int footModel);

    public abstract int getBodyModel();

    public abstract void setBodyModel(int bodyModel);

    public abstract int getBackModel();

    public abstract void setBackModel(int backModel);

    public abstract int getShoulderModel();

    public abstract void setShoulderModel(int shoulderModel);

    public abstract int getWingModel();

    public abstract void setWingModel(int wingModel);

    public abstract float getHeadScale();

    public abstract void setHeadScale(float headScale);

    public abstract float getHairScale();

    public abstract void setHairScale(float hairScale);

    public abstract float getHandScale();

    public abstract void setHandScale(float handScale);

    public abstract float getFootScale();

    public abstract void setFootScale(float footScale);

    public abstract float getBodyScale();

    public abstract void setBodyScale(float bodyScale);

    public abstract float getBackScale();

    public abstract void setBackScale(float backScale);

    public abstract float getUnknown();

    public abstract void setUnknown(float unknown);

    public abstract float getWingScale();

    public abstract void setWingScale(float wingScale);

    public abstract float getBodyPitch();

    public abstract void setBodyPitch(float bodyPitch);

    public abstract float getArmPitch();

    public abstract void setArmPitch(float armPitch);

    public abstract float getArmRoll();

    public abstract void setArmRoll(float armRoll);

    public abstract float getArmYaw();

    public abstract void setArmYaw(float armYaw);

    public abstract float getFeetPitch();

    public abstract void setFeetPitch(float feetPitch);

    public abstract float getWingPitch();

    public abstract void setWingPitch(float wingPitch);

    public abstract float getBackPitch();

    public abstract void setBackPitch(float backPitch);

    public abstract FloatVector3 getBodyOffset();

    public abstract void setBodyOffset(FloatVector3 bodyOffset);

    public abstract FloatVector3 getHeadOffset();

    public abstract void setHeadOffset(FloatVector3 headOffset);

    public abstract FloatVector3 getHandOffset();

    public abstract void setHandOffset(FloatVector3 handOffset);

    public abstract FloatVector3 getFootOffset();

    public abstract void setFootOffset(FloatVector3 footOffset);

    public abstract FloatVector3 getBackOffset();

    public abstract void setBackOffset(FloatVector3 backOffset);

    public abstract FloatVector3 getWingOffset();

    public abstract void setWingOffset(FloatVector3 wingOffset);

    public abstract float getShoulderScale();

    public abstract void setShoulderScale(float shoulderScale);

    public abstract float getWeaponScale();

    public abstract void setWeaponScale(float weaponScale);

}
