package org.glydar.core.model.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.glydar.api.model.geom.IntVector2;

public class ChunkItems {

    private final IntVector2      position;
    private final List<ChunkItem> items;

    public ChunkItems(IntVector2 position) {
        this.position = position;
        this.items = new ArrayList<>();
    }

    public IntVector2 getPosition() {
        return position;
    }

    public List<ChunkItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(ChunkItem item) {
        items.add(item);
    }
}
