package org.glydar.api.entity;

import java.util.BitSet;

public class EntityChanges {

    private final BitSet bitSet;

    public static EntityChanges all() {
        BitSet bitSet = new BitSet(64);
        bitSet.set(0, EntityChange.values().length);
        return new EntityChanges(bitSet);
    }

    public EntityChanges() {
        this(new BitSet(64));
    }

    public EntityChanges(BitSet bitSet) {
        this.bitSet = bitSet;
    }

    public boolean get(EntityChange change) {
        return bitSet.get(change.ordinal());
    }

    public byte[] toByteArray() {
        byte[] bitSetBytes = bitSet.toByteArray();
        byte[] bytes = new byte[64];
        System.arraycopy(bitSetBytes, 0, bytes, 0, bitSetBytes.length);
        return bytes;
    }
}
