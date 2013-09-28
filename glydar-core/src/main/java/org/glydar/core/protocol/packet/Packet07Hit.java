package org.glydar.core.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.core.model.entity.Entity;
import org.glydar.core.model.geom.FloatVector3;
import org.glydar.core.model.geom.LongVector3;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;
import org.glydar.core.protocol.codec.GeomCodec;

public class Packet07Hit implements Packet {

    private final long         damagerId;
    private final long         targetId;
    private final float        damage;
    private final byte         critical;
    private final byte[]       unknown5;
    private final long         stunDuration; // uint
    private final long         unknown7;    // uint
    private final LongVector3  position;
    private final FloatVector3 hitDirection;
    private final byte         skillHit;
    private final byte         type;
    private final byte         showLight;
    private final byte         unknown13;

    public Packet07Hit(Entity entity) {
        this.damagerId = entity.getId();
        this.targetId = entity.getId();
        this.damage = -100F;
        this.critical = (byte) 0;
        this.unknown5 = new byte[3];
        this.stunDuration = 0;
        this.unknown7 = 0;
        this.position = entity.getData().getPosition();
        this.hitDirection = entity.getData().getExtraVelocity();
        this.skillHit = (byte) 0;
        this.type = (byte) 0;
        this.showLight = (byte) 0;
        this.unknown13 = (byte) 0;
    }

    public Packet07Hit(ByteBuf buf) {
        this.damagerId = buf.readLong();
        this.targetId = buf.readLong();
        this.damage = buf.readFloat();
        this.critical = buf.readByte();
        this.unknown5 = new byte[3];
        buf.readBytes(unknown5);
        this.stunDuration = buf.readUnsignedInt();
        this.unknown7 = buf.readUnsignedInt();
        this.position = GeomCodec.readLongVector3(buf);
        this.hitDirection = GeomCodec.readFloatVector3(buf);
        this.skillHit = buf.readByte();
        this.type = buf.readByte();
        this.showLight = buf.readByte();
        this.unknown13 = buf.readByte();
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.HIT;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        buf.writeLong(damagerId);
        buf.writeLong(targetId);
        buf.writeFloat(damage);
        buf.writeByte(critical);
        buf.writeBytes(unknown5);
        buf.writeInt((int) stunDuration);
        buf.writeInt((int) unknown7);
        GeomCodec.writeLongVector3(buf, position);
        GeomCodec.writeFloatVector3(buf, hitDirection);
        buf.writeByte(skillHit);
        buf.writeByte(type);
        buf.writeByte(showLight);
        buf.writeByte(unknown13);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }

    public long getDamagerId() {
        return damagerId;
    }

    public long getTargetId() {
        return targetId;
    }

    public float getDamage() {
        return damage;
    }

    public byte getCritical() {
        return critical;
    }

    public byte[] getUnknown5() {
        return unknown5;
    }

    public long getStunDuration() {
        return stunDuration;
    }

    public long getUnknown7() {
        return unknown7;
    }

    public LongVector3 getPosition() {
        return position;
    }

    public FloatVector3 getHitDirection() {
        return hitDirection;
    }

    public byte getSkillHit() {
        return skillHit;
    }

    public byte getType() {
        return type;
    }

    public byte getShowLight() {
        return showLight;
    }

    public byte getUnknown13() {
        return unknown13;
    }
}
