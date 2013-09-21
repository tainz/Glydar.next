package org.glydar.api.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChunkItems {

    private final int             chunkX;
    private final int             chunkY;
    private final List<ChunkItem> items;

    public ChunkItems(int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
        this.items = new ArrayList<>();
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkY() {
        return chunkY;
    }

    public List<ChunkItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(ChunkItem item) {
        items.add(item);
    }
}
