package org.glydar.core.model.actions;

import org.glydar.api.Glydar;
import org.glydar.api.model.entity.Entity;

public class KillAction {

    private final long killerId;
    private final long targetId;
    private int xp;

    public KillAction(long killerId, long targetId) {
        this(killerId, targetId, 0);
        xp = calculateXp(killerId, targetId);
    }

    public KillAction(long killerId, long targetId, int xp) {
        this.killerId = killerId;
        this.targetId = targetId;
        this.xp = xp;
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

    private int calculateXp(long killerId, long targetId) {
        Entity killer = Glydar.getServer().getEntityById(killerId);
        Entity target = Glydar.getServer().getEntityById(targetId);

        if (killer == null || target == null) {
            return 0;
        }

        if (killer.getData().getLevel() <= 0 && target.getData().getLevel() <= 0) {
            return 0;
        }

        // TODO: Possibly make this formula better/smarter?
        return Math.round((float) killer.getData().getLevel() / (float) target.getData().getLevel() * 5);
    }
}
