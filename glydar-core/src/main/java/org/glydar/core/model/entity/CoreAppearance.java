package org.glydar.core.model.entity;

import org.glydar.api.model.entity.Appearance;
import org.glydar.api.model.geom.FloatVector3;

/* Structures and data discovered by cuwo (http://github.com/matpow2) */
public class CoreAppearance implements Appearance {

    private byte notUsed1;
    private byte notUsed2;

    private byte hairR;
    private byte hairG;
    private byte hairB;

    private byte movementFlags;
    private byte entityFlags;

    private float scale;
    private float boundingRadius;
    private float boundingHeight;

    private int headModel;
    private int hairModel;
    private int handModel;
    private int footModel;
    private int bodyModel;
    private int backModel;
    private int shoulderModel;
    private int wingModel;

    private float headScale;
    private float hairScale;
    private float handScale;
    private float footScale;
    private float bodyScale;
    private float backScale;
    private float unknown;
    private float wingScale;
    private float shoulderScale;
    private float weaponScale;

    private float bodyPitch;
    private float armPitch;
    private float armRoll;
    private float armYaw;
    private float feetPitch;
    private float wingPitch;
    private float backPitch;

    private FloatVector3 bodyOffset;
    private FloatVector3 headOffset;
    private FloatVector3 handOffset;
    private FloatVector3 footOffset;
    private FloatVector3 backOffset;
    private FloatVector3 wingOffset;

    public CoreAppearance() {
        bodyOffset = new FloatVector3();
        headOffset = new FloatVector3();
        handOffset = new FloatVector3();
        footOffset = new FloatVector3();
        backOffset = new FloatVector3();
        wingOffset = new FloatVector3();
    }

    public CoreAppearance(Appearance a) {
        this.notUsed1 = a.getNotUsed1();
        this.notUsed2 = a.getNotUsed2();
        this.hairR = a.getHairR();
        this.hairG = a.getHairG();
        this.hairB = a.getHairB();
        this.movementFlags = a.getMovementFlags();
        this.entityFlags = a.getEntityFlags();
        this.scale = a.getScale();
        this.boundingRadius = a.getBoundingRadius();
        this.boundingHeight = a.getBoundingHeight();
        this.headModel = a.getHeadModel();
        this.hairModel = a.getHairModel();
        this.handModel = a.getHandModel();
        this.footModel = a.getFootModel();
        this.bodyModel = a.getBodyModel();
        this.backModel = a.getBackModel();
        this.shoulderModel = a.getShoulderModel();
        this.wingModel = a.getWingModel();
        this.headScale = a.getHeadScale();
        this.hairScale = a.getHairScale();
        this.handScale = a.getHandScale();
        this.footScale = a.getFootScale();
        this.bodyScale = a.getBodyScale();
        this.backScale = a.getBackScale();
        this.unknown = a.getUnknown();
        this.wingScale = a.getWingScale();
        this.bodyPitch = a.getBodyPitch();
        this.armPitch = a.getArmPitch();
        this.armRoll = a.getArmRoll();
        this.armYaw = a.getArmYaw();
        this.feetPitch = a.getFeetPitch();
        this.wingPitch = a.getWingPitch();
        this.backPitch = a.getBackPitch();
        this.bodyOffset = a.getBodyOffset();
        this.headOffset = a.getHeadOffset();
        this.handOffset = a.getHandOffset();
        this.footOffset = a.getFootOffset();
        this.backOffset = a.getBackOffset();
        this.wingOffset = a.getWingOffset();
    }

    @Override
    public byte getNotUsed1() {
        return notUsed1;
    }

    @Override
    public void setNotUsed1(byte notUsed1) {
        this.notUsed1 = notUsed1;
    }

    @Override
    public byte getNotUsed2() {
        return notUsed2;
    }

    @Override
    public void setNotUsed2(byte notUsed2) {
        this.notUsed2 = notUsed2;
    }

    @Override
    public byte getHairR() {
        return hairR;
    }

    @Override
    public void setHairR(byte hairR) {
        this.hairR = hairR;
    }

    @Override
    public byte getHairG() {
        return hairG;
    }

    @Override
    public void setHairG(byte hairG) {
        this.hairG = hairG;
    }

    @Override
    public byte getHairB() {
        return hairB;
    }

    @Override
    public void setHairB(byte hairB) {
        this.hairB = hairB;
    }

    @Override
    public byte getMovementFlags() {
        return movementFlags;
    }

    @Override
    public void setMovementFlags(byte movementFlags) {
        this.movementFlags = movementFlags;
    }

    @Override
    public byte getEntityFlags() {
        return entityFlags;
    }

    @Override
    public void setEntityFlags(byte entityFlags) {
        this.entityFlags = entityFlags;
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public float getBoundingRadius() {
        return boundingRadius;
    }

    @Override
    public void setBoundingRadius(float boundingRadius) {
        this.boundingRadius = boundingRadius;
    }

    @Override
    public float getBoundingHeight() {
        return boundingHeight;
    }

    @Override
    public void setBoundingHeight(float boundingHeight) {
        this.boundingHeight = boundingHeight;
    }

    @Override
    public int getHeadModel() {
        return headModel;
    }

    @Override
    public void setHeadModel(int headModel) {
        this.headModel = headModel;
    }

    @Override
    public int getHairModel() {
        return hairModel;
    }

    @Override
    public void setHairModel(int hairModel) {
        this.hairModel = hairModel;
    }

    @Override
    public int getHandModel() {
        return handModel;
    }

    @Override
    public void setHandModel(int handModel) {
        this.handModel = handModel;
    }

    @Override
    public int getFootModel() {
        return footModel;
    }

    @Override
    public void setFootModel(int footModel) {
        this.footModel = footModel;
    }

    @Override
    public int getBodyModel() {
        return bodyModel;
    }

    @Override
    public void setBodyModel(int bodyModel) {
        this.bodyModel = bodyModel;
    }

    @Override
    public int getBackModel() {
        return backModel;
    }

    @Override
    public void setBackModel(int backModel) {
        this.backModel = backModel;
    }

    @Override
    public int getShoulderModel() {
        return shoulderModel;
    }

    @Override
    public void setShoulderModel(int shoulderModel) {
        this.shoulderModel = shoulderModel;
    }

    @Override
    public int getWingModel() {
        return wingModel;
    }

    @Override
    public void setWingModel(int wingModel) {
        this.wingModel = wingModel;
    }

    @Override
    public float getHeadScale() {
        return headScale;
    }

    @Override
    public void setHeadScale(float headScale) {
        this.headScale = headScale;
    }

    @Override
    public float getHairScale() {
        return hairScale;
    }

    @Override
    public void setHairScale(float hairScale) {
        this.hairScale = hairScale;
    }

    @Override
    public float getHandScale() {
        return handScale;
    }

    @Override
    public void setHandScale(float handScale) {
        this.handScale = handScale;
    }

    @Override
    public float getFootScale() {
        return footScale;
    }

    @Override
    public void setFootScale(float footScale) {
        this.footScale = footScale;
    }

    @Override
    public float getBodyScale() {
        return bodyScale;
    }

    @Override
    public void setBodyScale(float bodyScale) {
        this.bodyScale = bodyScale;
    }

    @Override
    public float getBackScale() {
        return backScale;
    }

    @Override
    public void setBackScale(float backScale) {
        this.backScale = backScale;
    }

    @Override
    public float getUnknown() {
        return unknown;
    }

    @Override
    public void setUnknown(float unknown) {
        this.unknown = unknown;
    }

    @Override
    public float getWingScale() {
        return wingScale;
    }

    @Override
    public void setWingScale(float wingScale) {
        this.wingScale = wingScale;
    }

    @Override
    public float getBodyPitch() {
        return bodyPitch;
    }

    @Override
    public void setBodyPitch(float bodyPitch) {
        this.bodyPitch = bodyPitch;
    }

    @Override
    public float getArmPitch() {
        return armPitch;
    }

    @Override
    public void setArmPitch(float armPitch) {
        this.armPitch = armPitch;
    }

    @Override
    public float getArmRoll() {
        return armRoll;
    }

    @Override
    public void setArmRoll(float armRoll) {
        this.armRoll = armRoll;
    }

    @Override
    public float getArmYaw() {
        return armYaw;
    }

    @Override
    public void setArmYaw(float armYaw) {
        this.armYaw = armYaw;
    }

    @Override
    public float getFeetPitch() {
        return feetPitch;
    }

    @Override
    public void setFeetPitch(float feetPitch) {
        this.feetPitch = feetPitch;
    }

    @Override
    public float getWingPitch() {
        return wingPitch;
    }

    @Override
    public void setWingPitch(float wingPitch) {
        this.wingPitch = wingPitch;
    }

    @Override
    public float getBackPitch() {
        return backPitch;
    }

    @Override
    public void setBackPitch(float backPitch) {
        this.backPitch = backPitch;
    }

    @Override
    public FloatVector3 getBodyOffset() {
        return bodyOffset;
    }

    @Override
    public void setBodyOffset(FloatVector3 bodyOffset) {
        this.bodyOffset = bodyOffset;
    }

    @Override
    public FloatVector3 getHeadOffset() {
        return headOffset;
    }

    @Override
    public void setHeadOffset(FloatVector3 headOffset) {
        this.headOffset = headOffset;
    }

    @Override
    public FloatVector3 getHandOffset() {
        return handOffset;
    }

    @Override
    public void setHandOffset(FloatVector3 handOffset) {
        this.handOffset = handOffset;
    }

    @Override
    public FloatVector3 getFootOffset() {
        return footOffset;
    }

    @Override
    public void setFootOffset(FloatVector3 footOffset) {
        this.footOffset = footOffset;
    }

    @Override
    public FloatVector3 getBackOffset() {
        return backOffset;
    }

    @Override
    public void setBackOffset(FloatVector3 backOffset) {
        this.backOffset = backOffset;
    }

    @Override
    public FloatVector3 getWingOffset() {
        return wingOffset;
    }

    @Override
    public void setWingOffset(FloatVector3 wingOffset) {
        this.wingOffset = wingOffset;
    }

    @Override
    public float getShoulderScale() {
        return shoulderScale;
    }

    @Override
    public void setShoulderScale(float shoulderScale) {
        this.shoulderScale = shoulderScale;
    }

    @Override
    public float getWeaponScale() {
        return weaponScale;
    }

    @Override
    public void setWeaponScale(float weaponScale) {
        this.weaponScale = weaponScale;
    }
}
