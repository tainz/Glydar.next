package org.glydar.core.model.geom;

import java.util.Arrays;

public class Orientation {

    private final float roll;
    private final float pitch;
    private final float yaw;

    public Orientation(float roll, float pitch, float yaw) {
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public Orientation setRoll(float newRoll) {
        return new Orientation(newRoll, pitch, yaw);
    }

    public Orientation setPitch(float newPitch) {
        return new Orientation(roll, newPitch, yaw);
    }

    public Orientation setYaw(float newYaw) {
        return new Orientation(roll, pitch, newYaw);
    }

    public Orientation setRollAndPitch(float newRoll, float newPitch) {
        return new Orientation(newRoll, newPitch, yaw);
    }

    public Orientation setRollAndYaw(float newRoll, float newYaw) {
        return new Orientation(newRoll, pitch, newYaw);
    }

    public Orientation setPitchAndYaw(float newPitch, float newYaw) {
        return new Orientation(roll, newPitch, newYaw);
    }

    @Override
    public String toString() {
        return "Orientation(roll=" + roll + ", pitch=" + pitch + ", yaw=" + yaw + ")";
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new float[] { roll, pitch, yaw });
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Orientation)) {
            return false;
        }

        Orientation other = (Orientation) object;
        return roll == other.roll && pitch == other.pitch && yaw == other.yaw;
    }
}
