package org.glydar.server;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.glydar.api.BackendType;
import org.glydar.api.Server;
import org.glydar.api.model.entity.Entity;
import org.glydar.api.model.entity.Player;
import org.glydar.api.model.world.World;
import org.glydar.core.CoreBackend;
import org.glydar.core.model.entity.CorePlayer;
import org.glydar.core.model.entity.CoreEntity;
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
    private final HashMap<Integer,Entity>   connectedEntities;

    public GlydarServer() {
        super(NAME);

        this.config = new GlydarServerConfig(this);
        this.worlds = new ArrayList<>();
        this.connectedEntities = new HashMap<>();

        setUpWorlds();
    }

    void setUpWorlds() {
        for (GlydarServerConfig.WorldConfig worldConfig : config.getAllWorldsConfigs()) {
            CoreWorld world = new CoreWorld(worldConfig.getName(), worldConfig.getSeed());
            world.setPvpAllowed(worldConfig.isPvpAllowed());
            worlds.add(world);
        }
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
    
    public Entity getEntityById(int id) {
    	return connectedEntities.get(id);
    }
    
    public void unregisterEntity(int id){
    	connectedEntities.remove(id);
    }
    
	public void registerEntity(Entity e) {
		connectedEntities.put(((CoreEntity) e).getId(), e);
	}

    public List<Entity> getEntities() {
    	return Lists.newArrayList(connectedEntities.values());
    }
    
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        for (Entity e : connectedEntities.values()){
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
    }

    @Override
    public void handle(CorePlayer player, Packet08Stealth packet) {
    }

    @Override
    public void handle(CorePlayer player, Packet09Shoot packet) {
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
        player.joinWorld((CoreWorld) getDefaultWorld());

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
