package org.glydar.mitm;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.glydar.api.Glydar;
import org.glydar.api.logging.GlydarLogger;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.RemoteType;
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
import org.glydar.mitm.events.ClientPacketEvent;
import org.glydar.mitm.events.PacketEvent;

public class MitmServer implements ProtocolHandler<Relay> {

    private static final String LOGGER_PREFIX = "MITM Server";

    private final GlydarLogger logger;

    public MitmServer(GlydarMitm mitm) {
        this.logger = mitm.getLogger(getClass(), LOGGER_PREFIX);
    }

    @Override
    public GlydarLogger getLogger() {
        return logger;
    }

    @Override
    public RemoteType getRemoteType() {
        return RemoteType.CLIENT;
    }

    @Override
    public Relay createRemote(Channel channel, Object data) {
        logger.info("Connection from {0}", channel.remoteAddress());

        Relay relay = new Relay(channel);
        GlydarMitm.getInstance().getRelays().add(relay);
        relay.connectToServer();
        return relay;
    }

    @Override
    public void disconnect(Relay relay) {
        Set<Relay> relays = GlydarMitm.getInstance().getRelays();
        relay.shutdownGracefully();
        relays.remove(relay);
    }

    private void forward(Relay relay, Packet... packets) {
        List<Packet> packetsToSend = new ArrayList<>();
        for (Packet packet : packets) {
            PacketEvent event = new ClientPacketEvent(relay, packet);
            Glydar.getEventManager().callEvent(event);
            if (event.isCancelled()) {
                continue;
            }

            packetsToSend.add(packet);
            logger.fine("Relaying packet {0}", packet.getPacketType());
        }

        relay.sendToServer(packets);
    }

    @Override
    public void handle(Relay relay, Packet00EntityUpdate packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet02UpdateFinished packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet04WorldUpdate packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet05CurrentTime packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet06Interaction packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet07Hit packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet08Stealth packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet09Shoot packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet10Chat packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet11ChunkDiscovery packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet12SectorDiscovery packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet13MissionData packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet15Seed packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet16Join packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet17VersionExchange packet) {
        forward(relay, packet);
    }

    @Override
    public void handle(Relay relay, Packet18ServerFull packet) {
        forward(relay, packet);
    }
}
