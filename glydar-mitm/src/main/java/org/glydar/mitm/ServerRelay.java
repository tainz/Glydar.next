package org.glydar.mitm;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.protocol.Packet;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.driver.ProtocolInitializer;
import org.glydar.protocol.packet.Packet00EntityUpdate;
import org.glydar.protocol.packet.Packet02UpdateFinished;
import org.glydar.protocol.packet.Packet04WorldUpdate;
import org.glydar.protocol.packet.Packet05CurrentTime;
import org.glydar.protocol.packet.Packet06Interaction;
import org.glydar.protocol.packet.Packet07Hit;
import org.glydar.protocol.packet.Packet08Stealth;
import org.glydar.protocol.packet.Packet09Shoot;
import org.glydar.protocol.packet.Packet10Chat;
import org.glydar.protocol.packet.Packet11ChunkDiscovery;
import org.glydar.protocol.packet.Packet12SectorDiscovery;
import org.glydar.protocol.packet.Packet13MissionData;
import org.glydar.protocol.packet.Packet15Seed;
import org.glydar.protocol.packet.Packet16Join;
import org.glydar.protocol.packet.Packet17VersionExchange;
import org.glydar.protocol.packet.Packet18ServerFull;

public class ServerRelay implements ProtocolHandler<CubeWorldServer>, Remote {

    private static final String  LOGGER_PREFIX = "Glydar Server Relay";
    private static final int     CLIENT_PORT   = 12345;

    private final GlydarLogger   logger;
    private final ClientRelay    clientRelay;
    private final EventLoopGroup workerGroup;
    private final List<Packet>   packetsQueue;
    private CubeWorldServer      cubeWorldServer;

    public ServerRelay(ClientRelay clientRelay) {
        this.logger = clientRelay.getLogger().getChildLogger(this, LOGGER_PREFIX);
        this.clientRelay = clientRelay;
        this.workerGroup = new NioEventLoopGroup();
        this.packetsQueue = new ArrayList<>();

        Bootstrap serverRelayBootstrap = new Bootstrap();
        serverRelayBootstrap.group(workerGroup);
        serverRelayBootstrap.channel(NioSocketChannel.class);
        serverRelayBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        serverRelayBootstrap.handler(new ProtocolInitializer<CubeWorldServer>(logger, this));
        serverRelayBootstrap.connect("localhost", CLIENT_PORT);
    }

    public void shutdownGracefully() {
        if (cubeWorldServer != null) {
            cubeWorldServer.closeConnection();
        }
        workerGroup.shutdownGracefully();
    }

    @Override
    public CubeWorldServer createRemote(ChannelHandlerContext context) {
        logger.info("Connected to Cube World Server");
        cubeWorldServer = new CubeWorldServer(context);

        cubeWorldServer.send(packetsQueue.toArray(new Packet[packetsQueue.size()]));
        packetsQueue.clear();
        return cubeWorldServer;
    }

    @Override
    public void disconnect(CubeWorldServer remote) {
        shutdownGracefully();
    }

    public void send(Packet... packets) {
        if (cubeWorldServer == null) {
            Collections.addAll(packetsQueue, packets);
        }
        else {
            cubeWorldServer.send(packets);
        }
    }

    private void forward(Packet... packets) {
        for (Packet packet : packets) {
            logger.fine("Forwarding packet {0} to client", packet.getPacketType());
        }

        clientRelay.send(packets);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet00EntityUpdate packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet02UpdateFinished packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet04WorldUpdate packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet05CurrentTime packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet06Interaction packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet07Hit packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet08Stealth packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet09Shoot packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet10Chat packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet11ChunkDiscovery packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet12SectorDiscovery packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet13MissionData packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet15Seed packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet16Join packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet17VersionExchange packet) {
        forward(packet);
    }

    @Override
    public void handle(CubeWorldServer remote, Packet18ServerFull packet) {
        forward(packet);
    }
}
