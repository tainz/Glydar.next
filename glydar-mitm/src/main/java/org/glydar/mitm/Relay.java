package org.glydar.mitm;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.glydar.api.Glydar;
import org.glydar.api.logging.GlydarLogger;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;
import org.glydar.core.protocol.driver.ProtocolInitializer;
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

public class Relay implements ProtocolHandler<VanillaServer>, Remote {

    private static final String  LOGGER_PREFIX = "MITM Relay ";

    private final GlydarLogger   logger;
    private final MitmServer     mitmServer;
    private final Channel        clientChannel;
    private final EventLoopGroup workerGroup;
    private final List<Packet>   packetsQueue;
    private VanillaServer        vanillaServer;
    private long                 entityId;

    public Relay(MitmServer mitmServer, Channel clientChannel) {
        this.logger = Glydar.getLogger(getClass(), LOGGER_PREFIX + clientChannel.remoteAddress());
        this.mitmServer = mitmServer;
        this.clientChannel = clientChannel;
        this.workerGroup = new NioEventLoopGroup();
        this.packetsQueue = new ArrayList<>();
        this.vanillaServer = null;
        this.entityId = -1;

        Bootstrap serverRelayBootstrap = new Bootstrap();
        serverRelayBootstrap.group(workerGroup);
        serverRelayBootstrap.channel(NioSocketChannel.class);
        serverRelayBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        serverRelayBootstrap.handler(new ProtocolInitializer<VanillaServer>(this));
        serverRelayBootstrap.connect("localhost", GlydarMitmMain.getVanillaPort());
    }

    @Override
    public GlydarLogger getLogger() {
        return logger;
    }

    @Override
    public RemoteType getRemoteType() {
        return RemoteType.SERVER;
    }

    public long getEntityId() {
        return entityId;
    }

    public void shutdownGracefully() {
        if (vanillaServer != null) {
            vanillaServer.closeConnection();
        }
        workerGroup.shutdownGracefully();
    }

    @Override
    public VanillaServer createRemote(Channel channel) {
        logger.info("Connected to Cube World Server");
        vanillaServer = new VanillaServer(channel);

        vanillaServer.send(packetsQueue.toArray(new Packet[packetsQueue.size()]));
        packetsQueue.clear();
        return vanillaServer;
    }

    @Override
    public void disconnect(VanillaServer remote) {
        mitmServer.disconnect(this);
    }

    public void send(Packet... packets) {
        for (Packet packet : packets) {
            logger.fine("Sending packet {0} to server", packet.getPacketType());
        }

        if (vanillaServer == null) {
            Collections.addAll(packetsQueue, packets);
        }
        else {
            vanillaServer.send(packets);
        }
    }

    private void forward(Packet... packets) {
        for (Packet packet : packets) {
            logger.fine("Sending packet {0} to client {1}", packet.getPacketType(), clientChannel.remoteAddress());
            clientChannel.write(packet);
        }
        clientChannel.flush();
    }

    @Override
    public void handle(VanillaServer remote, Packet00EntityUpdate packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet02UpdateFinished packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet04WorldUpdate packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet05CurrentTime packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet06Interaction packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet07Hit packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet08Stealth packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet09Shoot packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet10Chat packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet11ChunkDiscovery packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet12SectorDiscovery packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet13MissionData packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet15Seed packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet16Join packet) {
        this.entityId = packet.getId();
        logger.info("Entity Id : {0}", entityId);

        forward(packet, new Packet10Chat("Using Glydar MITM"));
    }

    @Override
    public void handle(VanillaServer remote, Packet17VersionExchange packet) {
        forward(packet);
    }

    @Override
    public void handle(VanillaServer remote, Packet18ServerFull packet) {
        forward(packet);
    }
}
