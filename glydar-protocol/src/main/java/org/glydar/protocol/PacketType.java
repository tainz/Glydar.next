package org.glydar.protocol;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.exceptions.InvalidPacketIdException;
import org.glydar.protocol.exceptions.UnsupportedPacketException;
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

public enum PacketType {

    ENTITY_UPDATE {

        @Override
        public Packet00EntityUpdate createPacket(ByteBuf buf) {
            return new Packet00EntityUpdate(buf);
        }
    },

    MULTIPLE_ENTITY_UPDATE {

        @Override
        public Packet createPacket(ByteBuf buf) {
            throw new UnsupportedPacketException(this);
        }
    },

    UPDATE_FINISHED {

        @Override
        public Packet02UpdateFinished createPacket(ByteBuf buf) {
            return new Packet02UpdateFinished();
        }
    },

    UNKOWN_3 {

        @Override
        public Packet createPacket(ByteBuf buf) {
            throw new UnsupportedPacketException(this);
        }
    },

    WORLD_UPDATE {

        @Override
        public Packet04WorldUpdate createPacket(ByteBuf buf) {
            return new Packet04WorldUpdate(buf);
        }
    },

    CURRENT_TIME {

        @Override
        public Packet05CurrentTime createPacket(ByteBuf buf) {
            return new Packet05CurrentTime(buf);
        }
    },

    INTERACTION {

        @Override
        public Packet06Interaction createPacket(ByteBuf buf) {
            return new Packet06Interaction(buf);
        }
    },

    HIT {

        @Override
        public Packet07Hit createPacket(ByteBuf buf) {
            return new Packet07Hit(buf);
        }
    },

    STEALTH {

        @Override
        public Packet08Stealth createPacket(ByteBuf buf) {
            return new Packet08Stealth(buf);
        }
    },

    SHOOT {

        @Override
        public Packet09Shoot createPacket(ByteBuf buf) {
            return new Packet09Shoot(buf);
        }
    },

    CHAT {

        @Override
        public Packet10Chat createPacket(ByteBuf buf) {
            return new Packet10Chat(buf);
        }
    },

    CHUNK_DISCOVERY {

        @Override
        public Packet11ChunkDiscovery createPacket(ByteBuf buf) {
            return new Packet11ChunkDiscovery(buf);
        }
    },

    SECTOR_DISCOVERY {

        @Override
        public Packet12SectorDiscovery createPacket(ByteBuf buf) {
            return new Packet12SectorDiscovery(buf);
        }
    },

    MISSION_DATA {

        @Override
        public Packet13MissionData createPacket(ByteBuf buf) {
            return new Packet13MissionData(buf);
        }
    },

    UNKNOWN_14 {

        @Override
        public Packet createPacket(ByteBuf buf) {
            throw new UnsupportedPacketException(this);
        }
    },

    SEED {

        @Override
        public Packet15Seed createPacket(ByteBuf buf) {
            return new Packet15Seed(buf);
        }
    },

    JOIN {

        @Override
        public Packet16Join createPacket(ByteBuf buf) {
            return new Packet16Join(buf);
        }
    },

    VERSION_EXCHANGE {

        @Override
        public Packet17VersionExchange createPacket(ByteBuf buf) {
            return new Packet17VersionExchange(buf);
        }
    },

    SERVER_FULL {

        @Override
        public Packet18ServerFull createPacket(ByteBuf buf) {
            return new Packet18ServerFull();
        }
    };

    public int id() {
        return ordinal();
    }

    public abstract Packet createPacket(ByteBuf buf);

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
