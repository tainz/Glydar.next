package org.glydar.core.model.item;

import org.glydar.api.model.item.Equipment;
import org.glydar.api.model.item.Item;

public class CoreEquipment implements Equipment {

    private Item unknown1;
    private Item neck;
    private Item chest;
    private Item feet;
    private Item hands;
    private Item shoulder;
    private Item weaponLeft;
    private Item weaponRight;
    private Item ringLeft;
    private Item ringRight;
    private Item light;
    private Item special;
    private Item pet;

    public CoreEquipment() {
        unknown1 = new CoreItem();
        neck = new CoreItem();
        chest = new CoreItem();
        feet = new CoreItem();
        hands = new CoreItem();
        shoulder = new CoreItem();
        weaponLeft = new CoreWeapon();
        weaponRight = new CoreWeapon();
        ringLeft = new CoreItem();
        ringRight = new CoreItem();
        light = new CoreItem();
        special = new CoreItem();
        pet = new CoreItem();
    }

    @Override
    public Item getUnknown1() {
        return unknown1;
    }

    @Override
    public void setUnknown1(Item unknown1) {
        this.unknown1 = unknown1;
    }

    @Override
    public Item getNeck() {
        return neck;
    }

    @Override
    public void setNeck(Item neck) {
        this.neck = neck;
    }

    @Override
    public Item getChest() {
        return chest;
    }

    @Override
    public void setChest(Item chest) {
        this.chest = chest;
    }

    @Override
    public Item getFeet() {
        return feet;
    }

    @Override
    public void setFeet(Item feet) {
        this.feet = feet;
    }

    @Override
    public Item getHands() {
        return hands;
    }

    @Override
    public void setHands(Item hands) {
        this.hands = hands;
    }

    @Override
    public Item getShoulder() {
        return shoulder;
    }

    @Override
    public void setShoulder(Item shoulder) {
        this.shoulder = shoulder;
    }

    @Override
    public Item getWeaponLeft() {
        return weaponLeft;
    }

    @Override
    public void setWeaponLeft(Item weaponLeft) {
        this.weaponLeft = weaponLeft;
    }

    @Override
    public Item getWeaponRight() {
        return weaponRight;
    }

    @Override
    public void setWeaponRight(Item weaponRight) {
        this.weaponRight = weaponRight;
    }

    @Override
    public Item getRingLeft() {
        return ringLeft;
    }

    @Override
    public void setRingLeft(Item ringLeft) {
        this.ringLeft = ringLeft;
    }

    @Override
    public Item getRingRight() {
        return ringRight;
    }

    @Override
    public void setRingRight(Item ringRight) {
        this.ringRight = ringRight;
    }

    @Override
    public Item getLight() {
        return light;
    }

    @Override
    public void setLight(Item light) {
        this.light = light;
    }

    @Override
    public Item getSpecial() {
        return special;
    }

    @Override
    public void setSpecial(Item special) {
        this.special = special;
    }

    @Override
    public Item getPet() {
        return pet;
    }

    @Override
    public void setPet(Item pet) {
        this.pet = pet;
    }
}
