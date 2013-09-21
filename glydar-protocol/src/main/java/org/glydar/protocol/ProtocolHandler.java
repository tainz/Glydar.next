package org.glydar.protocol;

import io.netty.channel.Channel;

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

public interface ProtocolHandler<T extends Remote> {

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
