package org.glydar.core.protocol;

import io.netty.channel.Channel;

import org.glydar.core.logging.CoreGlydarLogger;
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

public interface ProtocolHandler<T extends Remote> {

    CoreGlydarLogger getLogger();

    RemoteType getRemoteType();

    T createRemote(Channel channel);

    void disconnect(T remote);

    void handle(T remote, Packet00EntityUpdate packet);

    void handle(T remote, Packet02UpdateFinished packet);

    void handle(T remote, Packet04WorldUpdate packet);

    void handle(T remote, Packet05CurrentTime packet);

    void handle(T remote, Packet06Interaction packet);

    void handle(T remote, Packet07Hit packet);

    void handle(T remote, Packet08Stealth packet);

    void handle(T remote, Packet09Shoot packet);

    void handle(T remote, Packet10Chat packet);

    void handle(T remote, Packet11ChunkDiscovery packet);

    void handle(T remote, Packet12SectorDiscovery packet);

    void handle(T remote, Packet13MissionData packet);

    void handle(T remote, Packet15Seed packet);

    void handle(T remote, Packet16Join packet);

    void handle(T remote, Packet17VersionExchange packet);

    void handle(T remote, Packet18ServerFull packet);
}
