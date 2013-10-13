package org.glydar.api.model.entity.component;

public class LevelComponent implements Component {

    private long level;
    private long xp;

    public LevelComponent(long level, long xp) {
        this.level = level;
        this.xp = xp;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }
}
