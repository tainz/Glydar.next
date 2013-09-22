package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.RemoteType;

public class Packet13MissionData implements Packet {

    private final int   sectorX;
    private final int   sectorY;
    private final long  something3; // uint
    private final long  something4; // uint
    private final long  something5; // uint
    private final long  monsterId;  // uint
    private final long  questLevel; // uint
    private final short something8; // ubyte
    private final short something9; // ubyte
    private final float something10;
    private final float something11;
    private final long  chunkX;     // uint
    private final long  chunkY;     // uint

    public Packet13MissionData(ByteBuf buf) {
        this.sectorX = buf.readInt() / 8;
        this.sectorY = buf.readInt() / 8;
        this.something3 = buf.readUnsignedInt();
        this.something4 = buf.readUnsignedInt();
        this.something5 = buf.readUnsignedInt();
        this.monsterId = buf.readUnsignedInt();
        this.questLevel = buf.readUnsignedInt();
        this.something8 = buf.readUnsignedByte();
        this.something9 = buf.readUnsignedByte();
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
        buf.writeInt(sectorX * 8);
        buf.writeInt(sectorY * 8);
        buf.writeLong(something3);
        buf.writeLong(something4);
        buf.writeLong(something5);
        buf.writeLong(monsterId);
        buf.writeLong(questLevel);
        buf.writeShort(something8);
        buf.writeShort(something9);
        buf.writeZero(2);
        buf.writeFloat(something10);
        buf.writeFloat(something11);
        buf.writeLong(chunkX);
        buf.writeLong(chunkY);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }

    public int getSectorX() {
        return sectorX;
    }

    public int getSectorY() {
        return sectorY;
    }

    public long getSomething3() {
        return something3;
    }

    public long getSomething4() {
        return something4;
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

    public short getSomething9() {
        return something9;
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
