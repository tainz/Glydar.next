package org.glydar.core.model.actions;

import org.glydar.core.model.geom.FloatVector3;

public class SoundAction {

    private final FloatVector3 position;
    private final int          soundType;
    private float              pitch;
    private float              volume;

    public SoundAction(FloatVector3 position, int type) {
        this.position = position;
        this.soundType = type;
        this.pitch = 0f;
        this.volume = 0f;
    }

    public FloatVector3 getPosition() {
        return position;
    }

    public int getSoundType() {
        return soundType;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public enum SoundType {

        HIT,

        BLADE1,

        BLADE2;
    }
}
