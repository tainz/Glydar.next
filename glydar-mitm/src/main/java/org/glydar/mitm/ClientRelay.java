package org.glydar.mitm;

import io.netty.channel.ChannelHandlerContext;

import org.glydar.api.Glydar;
import org.glydar.api.logging.GlydarLogger;
import org.glydar.protocol.Packet;
import org.glydar.protocol.ProtocolHandler;
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

public class ClientRelay implements ProtocolHandler<ServerRelay> {

    private static final String   LOGGER_PREFIX = "Glydar Client Relay";

    private final GlydarLogger    logger;
    private ChannelHandlerContext context;
    private ServerRelay           serverRelay;

    public ClientRelay() {
        this.logger = Glydar.getLogger().getChildLogger(this, LOGGER_PREFIX);
        this.context = null;
        this.serverRelay = null;
    }

    public GlydarLogger getLogger() {
        return logger;
    }

    @Override
    public ServerRelay createRemote(ChannelHandlerContext context) {
        logger.info("Connection from {0}", context.channel().remoteAddress());

        this.context = context;
        this.serverRelay = new ServerRelay(this);
        return serverRelay;
    }

    public void send(Packet... packets) {
        for (Packet packet : packets) {
            logger.fine("Sending packet {0} to client", packet.getPacketType());
            context.write(packet);
        }
        context.flush();
    }

    public void shutdownGracefully() {
        serverRelay.shutdownGracefully();
    }

    public void doShutdownGracefully() {
        context.close();
    }

    @Override
    public void disconnect(ServerRelay client) {
        shutdownGracefully();
    }

    private void forward(Packet... packets) {
        serverRelay.send(packets);
    }

    @Override
    public void handle(ServerRelay client, Packet00EntityUpdate packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet02UpdateFinished packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet04WorldUpdate packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet05CurrentTime packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet06Interaction packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet07Hit packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet08Stealth packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet09Shoot packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet10Chat packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet11ChunkDiscovery packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet12SectorDiscovery packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet13MissionData packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet15Seed packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet16Join packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet17VersionExchange packet) {
        forward(packet);
    }

    @Override
    public void handle(ServerRelay client, Packet18ServerFull packet) {
        forward(packet);
    }
}
