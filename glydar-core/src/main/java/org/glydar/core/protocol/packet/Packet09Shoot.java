package org.glydar.core.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.api.model.geom.FloatVector3;
import org.glydar.api.model.geom.IntVector2;
import org.glydar.api.model.geom.LongVector3;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;
import org.glydar.core.protocol.codec.GeomCodec;

/* Structures and data discovered by cuwo (http://github.com/matpow2) */
public class Packet09Shoot implements Packet {

    private final long entityId; // Unsigned!

    private final IntVector2 chunkPosition;

    private final long something5; // Unsigned Int!

    private final LongVector3 position;

    private final long something13; // uint
    private final long something14; // uint
    private final long something15; // uint

    private final FloatVector3 velocity;

    private final float something19; // rand() ??
    private final float something20;
    private final float something21;
    private final float something22; // ?????
    private final long something23; // uint
    private final byte something24;
    private final long something25; // uint
    private final byte something26;
    private final long something27; // uint
    private final long something28; // uint

    public Packet09Shoot(ByteBuf buf) {
        this.entityId = buf.readLong(); // Unsigned long actually!
        this.chunkPosition = GeomCodec.readIntVector2(buf);
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
        buf.writeLong(entityId);
        GeomCodec.writeIntVector2(buf, chunkPosition);
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

    public long getEntityId() {
        return entityId;
    }

    public IntVector2 getChunkPosition() {
        return chunkPosition;
    }

    public long getSomething5() {
        return something5;
    }

    public LongVector3 getPosition() {
        return position;
    }

    public long getSomething13() {
        return something13;
    }

    public long getSomething14() {
        return something14;
    }

    public long getSomething15() {
        return something15;
    }

    public FloatVector3 getVelocity() {
        return velocity;
    }

    public float getSomething19() {
        return something19;
    }

    public float getSomething20() {
        return something20;
    }

    public float getSomething21() {
        return something21;
    }

    public float getSomething22() {
        return something22;
    }

    public float getSomething23() {
        return something23;
    }

    public float getSomething24() {
        return something24;
    }

    public float getSomething25() {
        return something25;
    }

    public float getSomething26() {
        return something26;
    }

    public float getSomething27() {
        return something27;
    }

    public float getSomething28() {
        return something28;
    }
}
