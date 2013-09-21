package org.glydar.api.actions;

public class KillAction {

    private final long killerId;
    private final long targetId;
    private int        xp;

    public KillAction(long killerId, long targetId) {
        this.killerId = killerId;
        this.targetId = targetId;
        this.xp = 0;
    }

    public long getKillerId() {
        return killerId;
    }

    public long getTargetId() {
        return targetId;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
