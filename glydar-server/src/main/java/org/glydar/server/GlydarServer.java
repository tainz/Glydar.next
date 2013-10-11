package org.glydar.server;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.glydar.api.BackendType;
import org.glydar.api.Server;
import org.glydar.api.model.entity.Entity;
import org.glydar.api.model.entity.EntityData;
import org.glydar.api.model.entity.Player;
import org.glydar.api.model.world.World;
import org.glydar.api.plugin.event.EventPriority;
import org.glydar.api.plugin.event.events.EntityAccelerationUpdateEvent;
import org.glydar.api.plugin.event.events.EntityExtraVelocityUpdateEvent;
import org.glydar.api.plugin.event.events.EntityFlagsUpdateEvent;
import org.glydar.api.plugin.event.events.EntityOrientationUpdateEvent;
import org.glydar.api.plugin.event.events.EntityPositionUpdateEvent;
import org.glydar.api.plugin.event.events.EntityVelocityUpdateEvent;
import org.glydar.core.CoreBackend;
import org.glydar.core.model.actions.KillAction;
import org.glydar.core.model.entity.CorePlayer;
import org.glydar.core.model.entity.CoreEntity;
import org.glydar.core.model.entity.EntityChange;
import org.glydar.core.model.entity.EntityChanges;
import org.glydar.core.model.world.CoreWorld;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.RemoteType;
import org.glydar.core.protocol.exceptions.ServerOnlyPacketException;
import org.glydar.core.protocol.packet.Packet00EntityUpdate;
import org.glydar.core.protocol.packet.Packet02UpdateFinished;
import org.glydar.core.protocol.packet.Packet04WorldUpdate;
import org.glydar.core.protocol.packet.Packet05CurrentTime;
import org.glydar.core.protocol.packet.Packet06Interaction;
import org.glydar.core.protocol.packet.Packet07Hit;
import org.glydar.core.protocol.packet.Packet08Stealth;
import org.glydar.core.protocol.packet.Packet09Shoot;
import org.glydar.core.protocol.packet.Packet10Chat;
import org.glydar.core.protocol.packet.Packet11ChunkDiscovery;
import org.glydar.core.protocol.packet.Packet12SectorDiscovery;
import org.glydar.core.protocol.packet.Packet13MissionData;
import org.glydar.core.protocol.packet.Packet15Seed;
import org.glydar.core.protocol.packet.Packet16Join;
import org.glydar.core.protocol.packet.Packet17VersionExchange;
import org.glydar.core.protocol.packet.Packet18ServerFull;

import com.google.common.collect.Lists;

public class GlydarServer extends CoreBackend implements Server, ProtocolHandler<CorePlayer> {

    private static final String      		NAME             = "Glydar";
    //TODO: Better place to  put this?
    private static final String		 		VERSION		  = "0.0.1-SNAPSHOT";
    private static final String				JOIN_MESSAGE	  = "Server powered by Glydar " + VERSION;

    private final GlydarServerConfig 		config;
    private final List<World>        		worlds;
    private final HashMap<Long,Entity>   entities;

    public GlydarServer() {
        super(NAME);

        this.config = new GlydarServerConfig(this);
        this.worlds = new ArrayList<>();
        this.entities = new HashMap<>();

        setUpWorlds();
        registerListeners();
    }

    void setUpWorlds() {
        for (GlydarServerConfig.WorldConfig worldConfig : config.getAllWorldsConfigs()) {
            CoreWorld world = new CoreWorld(worldConfig.getName(), worldConfig.getSeed());
            world.setPvpAllowed(worldConfig.isPvpAllowed());
            worlds.add(world);
        }
    }
    
    private void registerListeners() {
    	//Not sure if passing null will break anything?
    	getEventManager().register(null, EntityFlagsUpdateEvent.class, new DefaultPVPListener(), EventPriority.LOWEST);
    }

    @Override
    public BackendType getType() {
        return BackendType.SERVER;
    }

    @Override
    public void shutdown() {
        Packet10Chat chatPacket = new Packet10Chat("Stopping server, bye !");
        for (Player player : getPlayers()) {
            CorePlayer corePlayer = ((CorePlayer) player);
            corePlayer.sendPackets(chatPacket);
            corePlayer.remove();
        }

        getConsoleReader().interrupt();
        GlydarServerMain.shutdown();
    }

    public GlydarServerConfig getConfig() {
        return config;
    }

    public World getDefaultWorld() {
        return worlds.get(0);
    }

    public List<World> getWorlds() {
        return Lists.newArrayList(worlds);
    }
    
    public Entity getEntityById(long id) {
    	return entities.get(id);
    }
    
    public void unregisterEntity(long id){
    	entities.remove(id);
    }
    
	public void registerEntity(Entity e) {
		entities.put(((CoreEntity) e).getId(), e);
	}

    public List<Entity> getEntities() {
    	return Lists.newArrayList(entities.values());
    }
    
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        for (Entity e : entities.values()){
        	if (e instanceof Player){
        		players.add((Player) e);
        	}
        }
        return players;
    }

    @Override
    public GlydarServer getServer() {
        return this;
    }

    @Override
    public RemoteType getRemoteType() {
        return RemoteType.CLIENT;
    }

    @Override
    public CorePlayer createRemote(Channel channel, Object data) {
        return new CorePlayer(channel);
    }

    @Override
    public void disconnect(CorePlayer player) {
        getLogger().info("Player {0} left the server", player.getName());
        player.remove();
    }

    private void sendPacketsToAll(Packet... packets) {
        for (Player player : getPlayers()) {
            ((CorePlayer) player).sendPackets(packets);
        }
    }

    @Override
    public void handle(CorePlayer player, Packet00EntityUpdate packet) {
    	if (!player.isConnected()){
    		player.setConnected();
    		registerEntity(player);
    		player.getWorld().registerEntity(player);
    		//INSERT JOIN EVENT!
    	}
    	
    	//TODO: FINISH EVENTS
    	EntityData data = packet.getData();
    	EntityChanges otherChanges = packet.getData().getChanges();
    	if (otherChanges.get(EntityChange.POSITION)) {
            EntityPositionUpdateEvent event = getEventManager().callEvent(new EntityPositionUpdateEvent(player ,data.getPosition()));
            data.setPosition(event.getPosition());
        }
        if (otherChanges.get(EntityChange.ORIENTATION)) {
        	EntityOrientationUpdateEvent event = getEventManager().callEvent(new EntityOrientationUpdateEvent(player ,data.getOrientation()));
            data.setOrientation(event.getOrientation());
        }
        if (otherChanges.get(EntityChange.VELOCITY)) {
        	EntityVelocityUpdateEvent event = getEventManager().callEvent(new EntityVelocityUpdateEvent(player ,data.getVelocity()));
            data.setVelocity(event.getVelocity());
        }
        if (otherChanges.get(EntityChange.ACCELERATION)) {
        	EntityAccelerationUpdateEvent event = getEventManager().callEvent(new EntityAccelerationUpdateEvent(player ,data.getAcceleration()));
            data.setAcceleration(event.getAcceleration());
        }
        if (otherChanges.get(EntityChange.EXTRA_VELOCITY)) {
        	EntityExtraVelocityUpdateEvent event = getEventManager().callEvent(new EntityExtraVelocityUpdateEvent(player ,data.getExtraVelocity()));
            data.setExtraVelocity(event.getExtraVelocity());
        }
        if (otherChanges.get(EntityChange.LOOK_PITCH)) {
            
        }
        if (otherChanges.get(EntityChange.PHYSICS_FLAGS)) {
            
        }
        if (otherChanges.get(EntityChange.HOSTILE_TYPE)) {
            
        }
        if (otherChanges.get(EntityChange.ENTITY_TYPE)) {
            
        }
        if (otherChanges.get(EntityChange.CURRENT_MODE)) {
            
        }
        if (otherChanges.get(EntityChange.LAST_SHOOT_TIME)) {
            
        }
        if (otherChanges.get(EntityChange.HIT_COUNTER)) {
            
        }
        if (otherChanges.get(EntityChange.LAST_HIT_TIME)) {
            
        }
        if (otherChanges.get(EntityChange.APPEARANCE)) {
            
        }
        if (otherChanges.get(EntityChange.FLAGS)) {
        	EntityFlagsUpdateEvent event = getEventManager().callEvent(new EntityFlagsUpdateEvent(player ,data.getFlags1(), data.getFlags2()));
            data.setFlags1(event.getFlags1());
            data.setFlags2(event.getFlags2());
        }
        if (otherChanges.get(EntityChange.ROLL_TIME)) {
            
        }
        if (otherChanges.get(EntityChange.STUN_TIME)) {
            
        }
        if (otherChanges.get(EntityChange.SLOWED_TIME)) {
            
        }
        if (otherChanges.get(EntityChange.MAKE_BLUE_TIME)) {
            
        }
        if (otherChanges.get(EntityChange.SPEED_UP_TIME)) {
            
        }
        if (otherChanges.get(EntityChange.SLOW_PATCH_TIME)) {
            
        }
        if (otherChanges.get(EntityChange.ENTITY_CLASS)) {
            
        }
        if (otherChanges.get(EntityChange.SPECIALIZATION)) {
            
        }
        if (otherChanges.get(EntityChange.CHARGED_MP)) {
            
        }
        if (otherChanges.get(EntityChange.NU_1_2_3)) {
            
        }
        if (otherChanges.get(EntityChange.NU_4_5_6)) {
            
        }
        if (otherChanges.get(EntityChange.RAY_HIT)) {
           
        }
        if (otherChanges.get(EntityChange.HP)) {
         
        }
        if (otherChanges.get(EntityChange.MP)) {
            
        }
        if (otherChanges.get(EntityChange.BLOCK_POWER)) {
            
        }
        if (otherChanges.get(EntityChange.MULTIPLIERS)) {
            
        }
        if (otherChanges.get(EntityChange.NU_7)) {
           
        }
        if (otherChanges.get(EntityChange.NU_8)) {
           
        }
        if (otherChanges.get(EntityChange.LEVEL)) {
            
        }
        if (otherChanges.get(EntityChange.CURRENT_XP)) {
            
        }
        if (otherChanges.get(EntityChange.PARENT_OWNER)) {
           
        }
        if (otherChanges.get(EntityChange.NA_1_2)) {
            
        }
        if (otherChanges.get(EntityChange.NA_3)) {
           
        }
        if (otherChanges.get(EntityChange.NA_4)) {
           
        }
        if (otherChanges.get(EntityChange.NA_5_NU_11_12)) {
            
        }
        if (otherChanges.get(EntityChange.SPAWN_POSITION)) {
            
        }
        if (otherChanges.get(EntityChange.NU_20_21_22)) {
            
        }
        if (otherChanges.get(EntityChange.NU_19)) {
           
        }
        if (otherChanges.get(EntityChange.QUICK_ITEM)) {
           
        }
        if (otherChanges.get(EntityChange.EQUIPMENT)) {
            
        }
        if (otherChanges.get(EntityChange.NAME)) {
            
        }
        if (otherChanges.get(EntityChange.SKILLS)) {
            
        }
        if (otherChanges.get(EntityChange.ICE_BLOCK_FOUR)) {
            
        }
    	
        if (player.getId() == packet.getEntityId()) {
            player.getData().updateFrom(packet.getData());
        }
    }

    @Override
    public void handle(CorePlayer player, Packet02UpdateFinished packet) {
        throw new ServerOnlyPacketException(packet.getPacketType());
    }

    @Override
    public void handle(CorePlayer player, Packet04WorldUpdate packet) {
        throw new ServerOnlyPacketException(packet.getPacketType());
    }

    @Override
    public void handle(CorePlayer player, Packet05CurrentTime packet) {
        throw new ServerOnlyPacketException(packet.getPacketType());
    }

    @Override
    public void handle(CorePlayer player, Packet06Interaction packet) {
    }

    @Override
    public void handle(CorePlayer player, Packet07Hit packet) {
    	player.getWorld().getUpdateData().pushHit(packet);
    	
    	Entity target = getEntityById(packet.getTargetId());
    	if (target.getData().getHp() - packet.getDamage() <= 0){
    		player.getWorld().getUpdateData().pushKill(new KillAction(packet.getDamagerId(), packet.getTargetId()));
    	}
    	
    	if (!(target instanceof Player)){
    		target.getData().setHp(target.getData().getHp() - packet.getDamage());
    	}
    }

    @Override
    public void handle(CorePlayer player, Packet08Stealth packet) {
    }

    @Override
    public void handle(CorePlayer player, Packet09Shoot packet) {
    	player.getWorld().getUpdateData().pushShoot(packet);
    }

    @Override
    public void handle(CorePlayer player, Packet10Chat packet) {
        sendPacketsToAll(new Packet10Chat(player, packet.getMessage()));
    }

    @Override
    public void handle(CorePlayer player, Packet11ChunkDiscovery packet) {
    }

    @Override
    public void handle(CorePlayer player, Packet12SectorDiscovery packet) {
    }

    @Override
    public void handle(CorePlayer player, Packet13MissionData packet) {
        throw new ServerOnlyPacketException(packet.getPacketType());
    }

    @Override
    public void handle(CorePlayer player, Packet15Seed packet) {
        throw new ServerOnlyPacketException(packet.getPacketType());
    }

    @Override
    public void handle(CorePlayer player, Packet16Join packet) {
        throw new ServerOnlyPacketException(packet.getPacketType());
    }

    @Override
    public void handle(CorePlayer player, Packet17VersionExchange packet) {
        if (packet.getVersion() != ProtocolHandler.VERSION) {
            player.sendPackets(new Packet17VersionExchange(ProtocolHandler.VERSION));
            return;
        }

        if (getPlayers().size() >= config.getMaxPlayers()) {
            player.sendPackets(new Packet18ServerFull());
            return;
        }

        // TODO: Figure out in which world to put the player
        ((CorePlayer)player).initWorld((CoreWorld) getDefaultWorld());

        Packet16Join joinPacket = new Packet16Join(player);
        Packet15Seed seedPacket = new Packet15Seed(player.getWorld().getSeed());
        Packet10Chat chatPacket = new Packet10Chat(JOIN_MESSAGE);
        player.sendPackets(joinPacket, seedPacket, chatPacket);
    }

    @Override
    public void handle(CorePlayer player, Packet18ServerFull packet) {
        throw new ServerOnlyPacketException(packet.getPacketType());
    }

    public void tick() {
    	for (World w : worlds){
    		((CoreWorld) w).tick();
    	}
    }

}
