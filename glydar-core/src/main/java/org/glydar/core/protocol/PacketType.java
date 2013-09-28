package org.glydar.core.protocol;

import io.netty.buffer.ByteBuf;

import org.glydar.core.protocol.exceptions.InvalidPacketIdException;
import org.glydar.core.protocol.exceptions.UnsupportedPacketException;
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

public enum PacketType {

    ENTITY_UPDATE {

        @Override
        public Packet00EntityUpdate createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet00EntityUpdate(buf);
        }
    },

    MULTIPLE_ENTITY_UPDATE {

        @Override
        public Packet createPacket(RemoteType sender, ByteBuf buf) {
            throw new UnsupportedPacketException(this);
        }
    },

    UPDATE_FINISHED {

        @Override
        public Packet02UpdateFinished createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet02UpdateFinished();
        }
    },

    UNKOWN_3 {

        @Override
        public Packet createPacket(RemoteType sender, ByteBuf buf) {
            throw new UnsupportedPacketException(this);
        }
    },

    WORLD_UPDATE {

        @Override
        public Packet04WorldUpdate createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet04WorldUpdate(buf);
        }
    },

    CURRENT_TIME {

        @Override
        public Packet05CurrentTime createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet05CurrentTime(buf);
        }
    },

    INTERACTION {

        @Override
        public Packet06Interaction createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet06Interaction(buf);
        }
    },

    HIT {

        @Override
        public Packet07Hit createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet07Hit(buf);
        }
    },

    STEALTH {

        @Override
        public Packet08Stealth createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet08Stealth(buf);
        }
    },

    SHOOT {

        @Override
        public Packet09Shoot createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet09Shoot(buf);
        }
    },

    CHAT {

        @Override
        public Packet10Chat createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet10Chat(sender, buf);
        }
    },

    CHUNK_DISCOVERY {

        @Override
        public Packet11ChunkDiscovery createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet11ChunkDiscovery(buf);
        }
    },

    SECTOR_DISCOVERY {

        @Override
        public Packet12SectorDiscovery createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet12SectorDiscovery(buf);
        }
    },

    MISSION_DATA {

        @Override
        public Packet13MissionData createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet13MissionData(buf);
        }
    },

    UNKNOWN_14 {

        @Override
        public Packet createPacket(RemoteType sender, ByteBuf buf) {
            throw new UnsupportedPacketException(this);
        }
    },

    SEED {

        @Override
        public Packet15Seed createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet15Seed(buf);
        }
    },

    JOIN {

        @Override
        public Packet16Join createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet16Join(buf);
        }
    },

    VERSION_EXCHANGE {

        @Override
        public Packet17VersionExchange createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet17VersionExchange(buf);
        }
    },

    SERVER_FULL {

        @Override
        public Packet18ServerFull createPacket(RemoteType sender, ByteBuf buf) {
            return new Packet18ServerFull();
        }
    };

    public int id() {
        return ordinal();
    }

    public abstract Packet createPacket(RemoteType sender, ByteBuf buf);

    @Override
    public String toString() {
        return name() + "(" + id() + ")";
    }

    public static PacketType valueOf(int packetId) {
        PacketType[] types = values();
        if (packetId < 0 || packetId >= types.length) {
            throw new InvalidPacketIdException(packetId);
        }

        return types[packetId];
    }
}
