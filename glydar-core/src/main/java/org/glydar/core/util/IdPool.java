package org.glydar.core.util;

import java.util.HashSet;
import java.util.Set;

public class IdPool {

    private final Set<Long> set;
    private long lastId;

    public IdPool() {
        this.set = new HashSet<>();
        this.lastId = 0;
    }

    public long pop() {
        if (set.isEmpty()) {
            return ++lastId;
        }

        long id = set.iterator().next();
        set.remove(id);
        return id;
    }

    public void pushBack(long id) {
        set.add(id);
    }
}
