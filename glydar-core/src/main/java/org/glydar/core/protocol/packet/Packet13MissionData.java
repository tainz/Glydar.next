package org.glydar.core.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.api.model.geom.IntVector2;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;
import org.glydar.core.protocol.codec.GeomCodec;

public class Packet13MissionData implements Packet {

    private final IntVector2 sectorPosition;
    private final long       something1;    // uint
    private final long       something2;    // uint
    private final long       something3;    // uint
    private final long       missionId;     // uint
    private final long       something5;    // uint
    private final long       monsterId;     // uint
    private final long       questLevel;    // uint
    private final short      something8;    // ubyte
    private final short      state;         // ubyte
    private final float      something10;
    private final float      something11;
    private final long       chunkX;
    private final long       chunkY;

    public Packet13MissionData(ByteBuf buf) {
        this.sectorPosition = GeomCodec.readIntVector2(buf).divide(8);
        this.something1 = buf.readUnsignedInt();
        this.something2 = buf.readUnsignedInt();
        this.something3 = buf.readUnsignedInt();
        this.missionId = buf.readUnsignedInt();
        this.something5 = buf.readUnsignedInt();
        this.monsterId = buf.readUnsignedInt();
        this.questLevel = buf.readUnsignedInt();
        this.something8 = buf.readUnsignedByte();
        this.state = buf.readUnsignedByte();
        buf.skipBytes(2);
        this.something10 = buf.readFloat();
        this.something11 = buf.readFloat();
        this.chunkX = buf.readUnsignedInt();
        this.chunkY = buf.readUnsignedInt();
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.MISSION_DATA;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        GeomCodec.writeIntVector2(buf, sectorPosition.multiply(8));
        buf.writeInt((int) something1);
        buf.writeInt((int) something2);
        buf.writeInt((int) something3);
        buf.writeInt((int) missionId);
        buf.writeInt((int) something5);
        buf.writeInt((int) monsterId);
        buf.writeInt((int) questLevel);
        buf.writeByte(something8);
        buf.writeByte(state);
        buf.writeZero(2);
        buf.writeFloat(something10);
        buf.writeFloat(something11);
        buf.writeInt((int) chunkX);
        buf.writeInt((int) chunkY);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }

    public IntVector2 getSectorPosition() {
        return sectorPosition;
    }

    public long getSomething1() {
        return something1;
    }

    public long getSomething2() {
        return something2;
    }

    public long getSomething3() {
        return something3;
    }

    public long getMissionId() {
        return missionId;
    }

    public long getSomething5() {
        return something5;
    }

    public long getMonsterId() {
        return monsterId;
    }

    public long getQuestLevel() {
        return questLevel;
    }

    public short getSomething8() {
        return something8;
    }

    public short getState() {
        return state;
    }

    public float getSomething10() {
        return something10;
    }

    public float getSomething11() {
        return something11;
    }

    public long getChunX() {
        return chunkX;
    }

    public long getChunkY() {
        return chunkY;
    }
}
