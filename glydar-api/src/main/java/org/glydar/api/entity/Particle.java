package org.glydar.api.entity;

import org.glydar.api.geom.FloatVector3;
import org.glydar.api.geom.LongVector3;

public class Particle {

    private final LongVector3 position;
    private FloatVector3      acceleration;
    private float             colorRed;
    private float             colorBlue;
    private float             colorGreen;
    private float             colorAlpha;
    private float             scale;
    private int               count;
    private int               type;
    private float             spreading;
    private int               unknown18;

    public Particle(LongVector3 position) {
        this.position = position;
        this.acceleration = new FloatVector3();
        this.colorRed = 0f;
        this.colorBlue = 0f;
        this.colorGreen = 0f;
        this.colorAlpha = 0f;
        this.scale = 1f;
        this.count = 1;
        this.type = 0;
        this.spreading = 0f;
        this.unknown18 = 0;
    }

    public LongVector3 getPosition() {
        return position;
    }

    public FloatVector3 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(FloatVector3 acceleration) {
        this.acceleration = acceleration;
    }

    public float getColorRed() {
        return colorRed;
    }

    public void setColorRed(float colorRed) {
        this.colorRed = colorRed;
    }

    public float getColorBlue() {
        return colorBlue;
    }

    public void setColorBlue(float colorBlue) {
        this.colorBlue = colorBlue;
    }

    public float getColorGreen() {
        return colorGreen;
    }

    public void setColorGreen(float colorGreen) {
        this.colorGreen = colorGreen;
    }

    public float getColorAlpha() {
        return colorAlpha;
    }

    public void setColorAlpha(float colorAlpha) {
        this.colorAlpha = colorAlpha;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getSpreading() {
        return spreading;
    }

    public void setSpreading(float spreading) {
        this.spreading = spreading;
    }

    public int getUnknown18() {
        return unknown18;
    }

    public void setUnknown18(int unknown18) {
        this.unknown18 = unknown18;
    }
}
