package org.glydar.core.model.world;

import java.util.HashMap;

import org.glydar.api.model.entity.Entity;
import org.glydar.api.model.entity.Player;
import org.glydar.api.model.world.World;
import org.glydar.core.model.entity.CoreEntity;
import org.glydar.core.model.entity.CorePlayer;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.codec.WorldUpdates;
import org.glydar.core.protocol.packet.Packet00EntityUpdate;
import org.glydar.core.protocol.packet.Packet02UpdateFinished;
import org.glydar.core.protocol.packet.Packet04WorldUpdate;

import com.google.common.collect.ImmutableList;

public class CoreWorld implements World {

    private final String name;
    private final int seed;
    private boolean pvpAllowed;
    private final HashMap<Long, CoreEntity> entities;
    private final WorldUpdates updateData;

    public CoreWorld(String name, int seed) {
        this.name = name;
        this.seed = seed;
        this.pvpAllowed = false;
        this.entities = new HashMap<>();
        this.updateData = new WorldUpdates();
    }

    public ImmutableList<Entity> getEntities() {
        return ImmutableList.<Entity> copyOf(entities.values());
    }

    public ImmutableList<Player> getPlayers() {
        return ImmutableList.<Player> copyOf(getCorePlayers());
    }

    public ImmutableList<CorePlayer> getCorePlayers() {
        ImmutableList.Builder<CorePlayer> builder = ImmutableList.builder();
        for (Entity entity : entities.values()) {
            if (entity instanceof CorePlayer) {
                builder.add((CorePlayer) entity);
            }
        }
        return builder.build();
    }

    public CoreEntity getEntityById(long id) {
        return entities.get(id);
    }

    public void unregisterEntity(long id) {
        entities.remove(id);
    }

    public void registerEntity(Entity entity) {
        CoreEntity coreEntity = (CoreEntity) entity;
        entities.put(coreEntity.getId(), coreEntity);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSeed() {
        return seed;
    }

    @Override
    public boolean isPvpAllowed() {
        return pvpAllowed;
    }

    @Override
    public void setPvpAllowed(boolean pvpAllowed) {
        this.pvpAllowed = pvpAllowed;
        for (CorePlayer player : getCorePlayers()) {
            player.getData().setFlags1((byte) 32);
        }
    }

    public void sendPacketsToWorld(Packet... packets) {
        for (CorePlayer player : getCorePlayers()) {
            player.sendPackets(packets);
        }
    }

    public WorldUpdates getUpdateData() {
        return updateData;
    }

    public void tick() {
        for (CorePlayer p : getCorePlayers()) {
            sendPacketsToWorld(new Packet00EntityUpdate(p.getId(), p.getData()));
        }
        sendPacketsToWorld(new Packet02UpdateFinished());

        if (updateData.hasChanges()) {
            sendPacketsToWorld(new Packet04WorldUpdate(updateData));
        }
    }
}
