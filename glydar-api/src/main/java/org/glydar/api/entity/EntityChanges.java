package org.glydar.api.entity;

import java.util.BitSet;

public class EntityChanges {

    private final BitSet bitSet;

    public EntityChanges() {
        this.bitSet = new BitSet(64);
        bitSet.set(0, EntityChange.values().length);
    }

    public EntityChanges(BitSet bitSet) {
        this.bitSet = bitSet;
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    public boolean get(EntityChange change) {
        return bitSet.get(change.ordinal());
    }

    public void set(EntityChange change) {
        bitSet.set(change.ordinal());
    }

    public void reset() {
        bitSet.set(0, EntityChange.values().length, false);
    }
}
