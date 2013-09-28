package org.glydar.core.model.world;

import org.glydar.core.model.geom.LongVector3;
import org.glydar.core.model.item.Item;

public class ChunkItem {

    private final Item  item;
    private LongVector3 position;
    private float       rotation;
    private float       scale;
    private byte        unknown1;
    private long        dropTime;
    private long        unknown2;
    private int         unknown3;

    public ChunkItem(Item item) {
        this(item, new LongVector3());
    }

    public ChunkItem(Item item, LongVector3 position) {
        this.item = item;
        this.position = position;
        this.rotation = 0;
        this.scale = 0;
        this.unknown1 = 0;
        this.dropTime = 0;
        this.unknown2 = 0;
        this.unknown3 = 0;
    }

    public Item getItem() {
        return item;
    }

    public LongVector3 getPosition() {
        return position;
    }

    public void setPosition(LongVector3 position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public byte getUnknown1() {
        return unknown1;
    }

    public void setUnknown1(byte unknown1) {
        this.unknown1 = unknown1;
    }

    public long getDropTime() {
        return dropTime;
    }

    public void setDropTime(long dropTime) {
        this.dropTime = dropTime;
    }

    public long getUnknown2() {
        return unknown2;
    }

    public void setUnknown2(long unknown2) {
        this.unknown2 = unknown2;
    }

    public int getUnknown3() {
        return unknown3;
    }

    public void setUnknown3(int unknown3) {
        this.unknown3 = unknown3;
    }
}
