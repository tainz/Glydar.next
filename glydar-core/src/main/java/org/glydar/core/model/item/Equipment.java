package org.glydar.core.model.item;

public class Equipment {

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

    public Equipment() {
        unknown1 = new Item();
        neck = new Item();
        chest = new Item();
        feet = new Item();
        hands = new Item();
        shoulder = new Item();
        weaponLeft = new Weapon();
        weaponRight = new Weapon();
        ringLeft = new Item();
        ringRight = new Item();
        light = new Item();
        special = new Item();
        pet = new Item();
    }

    public Equipment(Equipment e) {
        this.unknown1 = e.getUnknown1();
        this.neck = e.getNeck();
        this.chest = e.getChest();
        this.feet = e.getFeet();
        this.hands = e.getHands();
        this.shoulder = e.getShoulder();
        this.weaponLeft = e.getWeaponLeft();
        this.weaponRight = e.getWeaponRight();
        this.ringLeft = e.getRingLeft();
        this.ringRight = e.getRingRight();
        this.light = e.getLight();
        this.special = e.getSpecial();
        this.pet = e.getPet();
    }

    public Item getUnknown1() {
        return unknown1;
    }

    public void setUnknown1(Item unknown1) {
        this.unknown1 = unknown1;
    }

    public Item getNeck() {
        return neck;
    }

    public void setNeck(Item neck) {
        this.neck = neck;
    }

    public Item getChest() {
        return chest;
    }

    public void setChest(Item chest) {
        this.chest = chest;
    }

    public Item getFeet() {
        return feet;
    }

    public void setFeet(Item feet) {
        this.feet = feet;
    }

    public Item getHands() {
        return hands;
    }

    public void setHands(Item hands) {
        this.hands = hands;
    }

    public Item getShoulder() {
        return shoulder;
    }

    public void setShoulder(Item shoulder) {
        this.shoulder = shoulder;
    }

    public Item getWeaponLeft() {
        return weaponLeft;
    }

    public void setWeaponLeft(Item weaponLeft) {
        this.weaponLeft = weaponLeft;
    }

    public Item getWeaponRight() {
        return weaponRight;
    }

    public void setWeaponRight(Item weaponRight) {
        this.weaponRight = weaponRight;
    }

    public Item getRingLeft() {
        return ringLeft;
    }

    public void setRingLeft(Item ringLeft) {
        this.ringLeft = ringLeft;
    }

    public Item getRingRight() {
        return ringRight;
    }

    public void setRingRight(Item ringRight) {
        this.ringRight = ringRight;
    }

    public Item getLight() {
        return light;
    }

    public void setLight(Item light) {
        this.light = light;
    }

    public Item getSpecial() {
        return special;
    }

    public void setSpecial(Item special) {
        this.special = special;
    }

    public Item getPet() {
        return pet;
    }

    public void setPet(Item pet) {
        this.pet = pet;
    }
}
