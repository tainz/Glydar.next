package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.api.geom.FloatVector3;
import org.glydar.api.geom.LongVector3;
import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.RemoteType;
import org.glydar.protocol.codec.GeomCodec;

public class Packet09Shoot implements Packet {

    private final long         entID;      // Unsigned!

    private final int          chunkX;
    private final int          chunkY;

    private final long         something5; // Unsigned Int!

    private final LongVector3  position;

    private final long         something13; // uint
    private final long         something14; // uint
    private final long         something15; // uint

    private final FloatVector3 velocity;

    private final float        something19; // rand() ??
    private final float        something20;
    private final float        something21;
    private final float        something22; // ?????
    private final long         something23; // uint
    private final byte         something24;
    private final long         something25; // uint
    private final byte         something26;
    private final long         something27; // uint
    private final long         something28; // uint

    public Packet09Shoot(ByteBuf buf) {
        this.entID = buf.readLong(); // Unsigned long actually!
        this.chunkX = buf.readInt();
        this.chunkY = buf.readInt();
        this.something5 = buf.readUnsignedInt();
        buf.skipBytes(4);
        this.position = GeomCodec.readLongVector3(buf);

        this.something13 = buf.readUnsignedInt();
        this.something14 = buf.readUnsignedInt();
        this.something15 = buf.readUnsignedInt();

        this.velocity = GeomCodec.readFloatVector3(buf);

        this.something19 = buf.readFloat();
        this.something20 = buf.readFloat();
        this.something21 = buf.readFloat();
        this.something22 = buf.readFloat();
        this.something23 = buf.readUnsignedInt();
        this.something24 = buf.readByte();
        buf.skipBytes(3);
        this.something25 = buf.readUnsignedInt();
        this.something26 = buf.readByte();
        buf.skipBytes(3);
        this.something27 = buf.readUnsignedInt();
        this.something28 = buf.readUnsignedInt();
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.SHOOT;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        buf.writeLong(entID);
        buf.writeInt(chunkX);
        buf.writeInt(chunkY);
        buf.writeInt((int) something5);
        buf.writeZero(4);
        GeomCodec.writeLongVector3(buf, position);
        buf.writeInt((int) something13);
        buf.writeInt((int) something14);
        buf.writeInt((int) something15);
        GeomCodec.writeFloatVector3(buf, velocity);
        buf.writeFloat(something19);
        buf.writeFloat(something20);
        buf.writeFloat(something21);
        buf.writeFloat(something22);
        buf.writeInt((int) something23);
        buf.writeByte(something24);
        buf.writeZero(3);
        buf.writeInt((int) something25);
        buf.writeByte(something26);
        buf.writeZero(3);
        buf.writeInt((int) something27);
        buf.writeInt((int) something28);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
