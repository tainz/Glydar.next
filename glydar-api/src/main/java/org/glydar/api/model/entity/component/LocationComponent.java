package org.glydar.api.model.entity.component;

import org.glydar.api.model.geom.LongVector3;
import org.glydar.api.model.geom.Orientation;

public class LocationComponent implements Component {

    private LongVector3 position;
    private Orientation orientation;
    private float lookPitch;
    private LongVector3 spawnPosition;

    public LocationComponent(LongVector3 position, Orientation orientation, float lookPitch) {
        this.position = position;
        this.orientation = orientation;
        this.lookPitch = lookPitch;
    }

    public LongVector3 getPosition() {
        return position;
    }

    public void setPosition(LongVector3 position) {
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public float getLookPitch() {
        return lookPitch;
    }

    public void setLookPitch(float lookPitch) {
        this.lookPitch = lookPitch;
    }

    public LongVector3 getSpawnPosition() {
        return spawnPosition;
    }

    public void setSpawnPosition(LongVector3 spawnPosition) {
        this.spawnPosition = spawnPosition;
    }
}
