package org.glydar.protocol.codec;

import io.netty.buffer.ByteBuf;

import java.util.BitSet;

import org.glydar.api.entity.Appearance;
import org.glydar.api.entity.EntityData;
import org.glydar.api.entity.Particle;
import org.glydar.api.geom.LongVector3;
import org.glydar.api.item.Item;

import com.google.common.base.Charsets;

public final class EntityCodec {

    private EntityCodec() {
    }

    public static EntityData readEntityData(ByteBuf buf) {
        EntityData e = new EntityData();
        e.setId(buf.readLong());

        byte[] bitSetBuf = new byte[8];
        buf.readBytes(bitSetBuf);
        e.setBitSet(BitSet.valueOf(bitSetBuf));
        BitSet bitSet = e.getBitSet();

        if (bitSet.get(0)) {
            e.setPosition(GeomCodec.readLongVector3(buf));
        }
        if (bitSet.get(1)) {
            e.setOrientation(GeomCodec.readOrientation(buf));
        }
        if (bitSet.get(2)) {
            e.setVelocity(GeomCodec.readFloatVector3(buf));
        }
        if (bitSet.get(3)) {
            e.setAccel(GeomCodec.readFloatVector3(buf));
        }
        if (bitSet.get(4)) {
            e.setExtraVel(GeomCodec.readFloatVector3(buf));
        }
        if (bitSet.get(5)) {
            e.setLookPitch(buf.readFloat());
        }
        if (bitSet.get(6)) {
            e.setPhysicsFlags(buf.readUnsignedInt());
        }
        if (bitSet.get(7)) {
            e.setHostileType(buf.readByte());
        }
        if (bitSet.get(8)) {
            e.setEntityType(buf.readUnsignedInt());
        }
        if (bitSet.get(9)) {
            e.setCurrentMode(buf.readByte());
        }
        if (bitSet.get(10)) {
            e.setLastShootTime(buf.readUnsignedInt());
        }
        if (bitSet.get(11)) {
            e.setHitCounter(buf.readUnsignedInt());
        }
        if (bitSet.get(12)) {
            e.setLastHitTime(buf.readUnsignedInt());
        }
        if (bitSet.get(13)) {
            e.setApp(readAppearance(buf));
        }
        if (bitSet.get(14)) {
            e.setFlags1(buf.readByte());
            e.setFlags2(buf.readByte());
        }
        if (bitSet.get(15)) {
            e.setRollTime(buf.readUnsignedInt());
        }
        if (bitSet.get(16)) {
            e.setStunTime(buf.readInt());
        }
        if (bitSet.get(17)) {
            e.setSlowedTime(buf.readUnsignedInt());
        }
        if (bitSet.get(18)) {
            e.setMakeBlueTime(buf.readUnsignedInt());
        }
        if (bitSet.get(19)) {
            e.setSpeedUpTime(buf.readUnsignedInt());
        }
        if (bitSet.get(20)) {
            e.setSlowPatchTime(buf.readFloat());
        }
        if (bitSet.get(21)) {
            e.setClassType(buf.readByte());
        }
        if (bitSet.get(22)) {
            e.setSpecialization(buf.readByte());
        }
        if (bitSet.get(23)) {
            e.setChargedMP(buf.readFloat());
        }
        if (bitSet.get(24)) {
            e.setNu1(buf.readUnsignedInt());
            e.setNu2(buf.readUnsignedInt());
            e.setNu3(buf.readUnsignedInt());
        }
        if (bitSet.get(25)) {
            e.setNu4(buf.readUnsignedInt());
            e.setNu5(buf.readUnsignedInt());
            e.setNu6(buf.readUnsignedInt());
        }
        if (bitSet.get(26)) {
            e.setRayHit(GeomCodec.readFloatVector3(buf));
        }
        if (bitSet.get(27)) {
            e.setHP(buf.readFloat());
        }
        if (bitSet.get(28)) {
            e.setMP(buf.readFloat());
        }
        if (bitSet.get(29)) {
            e.setBlockPower(buf.readFloat());
        }
        if (bitSet.get(30)) {
            e.setMaxHPMultiplier(buf.readFloat());
            e.setShootSpeed(buf.readFloat());
            e.setDamageMultiplier(buf.readFloat());
            e.setArmorMultiplier(buf.readFloat());
            e.setResistanceMultiplier(buf.readFloat());
        }
        if (bitSet.get(31)) {
            e.setNu7(buf.readByte());
        }
        if (bitSet.get(32)) {
            e.setNu8(buf.readByte());
        }
        if (bitSet.get(33)) {
            e.setLevel(buf.readUnsignedInt());
        }
        if (bitSet.get(34)) {
            e.setCurrentXP(buf.readUnsignedInt());
        }
        if (bitSet.get(35)) {
            e.setParentOwner(buf.readLong());
        }
        if (bitSet.get(36)) {
            e.setNa1(buf.readUnsignedInt());
            e.setNa2(buf.readUnsignedInt());
        }
        if (bitSet.get(37)) {
            e.setNa3(buf.readByte());
        }
        if (bitSet.get(38)) {
            e.setNa4(buf.readUnsignedInt());
        }
        if (bitSet.get(39)) {
            e.setNa5(buf.readUnsignedInt());
            e.setNu11(buf.readUnsignedInt());
            e.setNu12(buf.readUnsignedInt());
        }
        if (bitSet.get(40)) {
            e.setSpawnPosition(GeomCodec.readLongVector3(buf));
        }
        if (bitSet.get(41)) {
            e.setNu20(buf.readUnsignedInt());
            e.setNu21(buf.readUnsignedInt());
            e.setNu22(buf.readUnsignedInt());
        }
        if (bitSet.get(42)) {
            e.setNu19(buf.readByte());
        }
        if (bitSet.get(43)) {
            e.setItemData(ItemCodec.readItem(buf));
        }
        if (bitSet.get(44)) {
            Item[] equipment = e.getEquipment();
            for (int i = 0; i < 13; i++) {
                equipment[i] = ItemCodec.readItem(buf);
            }
            e.setEquipment(equipment);
        }
        if (bitSet.get(45)) {
            e.setName(new String(buf.readBytes(16).array(), Charsets.US_ASCII).trim());
        }
        if (bitSet.get(46)) {
            long[] skills = e.getSkills();
            for (int i = 0; i < 11; i++) {
                skills[i] = buf.readUnsignedInt();
            }
            e.setSkills(skills);
        }
        if (bitSet.get(47)) {
            e.setIceBlockFour(buf.readUnsignedInt());
        }

        return e;
    }

    public static void writeEntityData(ByteBuf buf, EntityData e) {
        // For testing purposes using default dummy entity :)
        /*
         * boolean dummy = false; if (name.contains("dummy")) dummy = true;
         */

        buf.writeLong(e.getId()); // Ulong but whatever
        BitSet bitSet = e.getBitSet();
        buf.writeBytes(bitSet.toByteArray());
        buf.writeBytes(new byte[8 - bitSet.toByteArray().length]); // BitSet/BitArray
                                                                   // are the
                                                                   // stupidest
                                                                   // classes
                                                                   // ever :(
        if (bitSet.get(0)) {
            GeomCodec.writeLongVector3(buf, e.getPosition());
        }
        if (bitSet.get(1)) {
            GeomCodec.writeOrientation(buf, e.getOrientation());
        }
        if (bitSet.get(2)) {
            GeomCodec.writeFloatVector3(buf, e.getVelocity());
        }
        if (bitSet.get(3)) {
            GeomCodec.writeFloatVector3(buf, e.getAccel());
        }
        if (bitSet.get(4)) {
            GeomCodec.writeFloatVector3(buf, e.getExtraVel());
        }
        if (bitSet.get(5)) {
            buf.writeFloat(e.getLookPitch());
        }
        if (bitSet.get(6)) {
            buf.writeInt((int) e.getPhysicsFlags());
        }
        if (bitSet.get(7)) {
            buf.writeByte(e.getHostileType());
        }
        if (bitSet.get(8)) {
            buf.writeInt((int) e.getEntityType());
        }
        if (bitSet.get(9)) {
            buf.writeByte(e.getCurrentMode());
        }
        if (bitSet.get(10)) {
            buf.writeInt((int) e.getLastShootTime());
        }
        if (bitSet.get(11)) {
            buf.writeInt((int) e.getHitCounter());
        }
        if (bitSet.get(12)) {
            buf.writeInt((int) e.getLastHitTime());
        }
        if (bitSet.get(13)) {
            writeAppearance(buf, e.getApp());
        }
        if (bitSet.get(14)) {
            buf.writeByte(e.getFlags1());
            buf.writeByte(e.getFlags2());
        }
        if (bitSet.get(15)) {
            buf.writeInt((int) e.getRollTime());
        }
        if (bitSet.get(16)) {
            buf.writeInt(e.getStunTime());
        }
        if (bitSet.get(17)) {
            buf.writeInt((int) e.getSlowedTime());
        }
        if (bitSet.get(18)) {
            buf.writeInt((int) e.getMakeBlueTime());
        }
        if (bitSet.get(19)) {
            buf.writeInt((int) e.getSpeedUpTime());
        }
        if (bitSet.get(20)) {
            buf.writeFloat(e.getSlowPatchTime());
        }
        if (bitSet.get(21)) {
            buf.writeByte(e.getClassType());
        }
        if (bitSet.get(22)) {
            buf.writeByte(e.getSpecialization());
        }
        if (bitSet.get(23)) {
            buf.writeFloat(e.getChargedMP());
        }
        if (bitSet.get(24)) {
            buf.writeInt((int) e.getNu1());
            buf.writeInt((int) e.getNu2());
            buf.writeInt((int) e.getNu3());
        }
        if (bitSet.get(25)) {
            buf.writeInt((int) e.getNu4());
            buf.writeInt((int) e.getNu5());
            buf.writeInt((int) e.getNu6());
        }
        if (bitSet.get(26)) {
            GeomCodec.writeFloatVector3(buf, e.getRayHit());
        }
        if (bitSet.get(27)) {
            buf.writeFloat(e.getHP());
        }
        if (bitSet.get(28)) {
            buf.writeFloat(e.getMP());
        }
        if (bitSet.get(29)) {
            buf.writeFloat(e.getBlockPower());
        }
        if (bitSet.get(30)) {
            buf.writeFloat(e.getMaxHPMultiplier());
            buf.writeFloat(e.getShootSpeed());
            buf.writeFloat(e.getDamageMultiplier());
            buf.writeFloat(e.getArmorMultiplier());
            buf.writeFloat(e.getResistanceMultiplier());
        }
        if (bitSet.get(31)) {
            buf.writeByte(e.getNu7());
        }
        if (bitSet.get(32)) {
            buf.writeByte(e.getNu8());
        }
        if (bitSet.get(33)) {
            buf.writeInt((int) e.getLevel());
        }
        if (bitSet.get(34)) {
            buf.writeInt((int) e.getCurrentXP());
        }
        if (bitSet.get(35)) {
            buf.writeLong(e.getParentOwner());
        }
        if (bitSet.get(36)) {
            buf.writeInt((int) e.getNa1());
            buf.writeInt((int) e.getNa2());
        }
        if (bitSet.get(37)) {
            buf.writeByte(e.getNa3());
        }
        if (bitSet.get(38)) {
            buf.writeInt((int) e.getNa4());
        }
        if (bitSet.get(39)) {
            buf.writeInt((int) e.getNa5());
            buf.writeInt((int) e.getNu11());
            buf.writeInt((int) e.getNu12());
        }
        if (bitSet.get(40)) {
            GeomCodec.writeLongVector3(buf, e.getSpawnPosition());
        }
        if (bitSet.get(41)) {
            buf.writeInt((int) e.getNu20());
            buf.writeInt((int) e.getNu21());
            buf.writeInt((int) e.getNu22());
        }
        if (bitSet.get(42)) {
            buf.writeByte(e.getNu19());
        }
        if (bitSet.get(43)) {
            ItemCodec.writeItem(buf, e.getItemData());
        }
        if (bitSet.get(44)) {
            Item[] equipment = e.getEquipment();
            for (int i = 0; i < 13; i++) {
                ItemCodec.writeItem(buf, equipment[i]);
            }
        }
        if (bitSet.get(45)) {
            byte[] ascii = e.getName().getBytes(Charsets.US_ASCII);
            buf.writeBytes(ascii);
            buf.writeBytes(new byte[16 - e.getName().length()]);
        }
        if (bitSet.get(46)) {
            long[] skills = e.getSkills();
            for (int i = 0; i < 11; i++) {
                buf.writeInt((int) skills[i]);
            }
        }
        if (bitSet.get(47)) {
            buf.writeInt((int) e.getIceBlockFour());
        }
    }

    public static Appearance readAppearance(ByteBuf buf) {
        Appearance a = new Appearance();
        a.setNotUsed1(buf.readByte());
        a.setNotUsed2(buf.readByte());
        a.setHairR(buf.readByte());
        a.setHairG(buf.readByte());
        a.setHairB(buf.readByte());
        buf.skipBytes(1);
        a.setMovementFlags(buf.readByte());
        a.setEntityFlags(buf.readByte());
        a.setScale(buf.readFloat());
        a.setBoundingRadius(buf.readFloat());
        a.setBoundingHeight(buf.readFloat());
        a.setHeadModel(buf.readUnsignedShort());
        a.setHairModel(buf.readUnsignedShort());
        a.setHandModel(buf.readUnsignedShort());
        a.setFootModel(buf.readUnsignedShort());
        a.setBodyModel(buf.readUnsignedShort());
        a.setBackModel(buf.readUnsignedShort());
        a.setShoulderModel(buf.readUnsignedShort());
        a.setWingModel(buf.readUnsignedShort());
        a.setHeadScale(buf.readFloat());
        a.setBodyScale(buf.readFloat());
        a.setHandScale(buf.readFloat());
        a.setFootScale(buf.readFloat());
        a.setShoulderScale(buf.readFloat());
        a.setWeaponScale(buf.readFloat());
        a.setBackScale(buf.readFloat());
        a.setUnknown(buf.readFloat());
        a.setWingScale(buf.readFloat());
        a.setBodyPitch(buf.readFloat());
        a.setArmPitch(buf.readFloat());
        a.setArmRoll(buf.readFloat());
        a.setArmYaw(buf.readFloat());
        a.setFeetPitch(buf.readFloat());
        a.setWingPitch(buf.readFloat());
        a.setBackPitch(buf.readFloat());
        a.setBodyOffset(GeomCodec.readFloatVector3(buf));
        a.setHeadOffset(GeomCodec.readFloatVector3(buf));
        a.setHandOffset(GeomCodec.readFloatVector3(buf));
        a.setFootOffset(GeomCodec.readFloatVector3(buf));
        a.setBackOffset(GeomCodec.readFloatVector3(buf));
        a.setWingOffset(GeomCodec.readFloatVector3(buf));
        return a;
    }

    public static void writeAppearance(ByteBuf buf, Appearance a) {
        buf.writeByte(a.getNotUsed1());
        buf.writeByte(a.getNotUsed2());
        buf.writeByte(a.getHairR());
        buf.writeByte(a.getHairG());
        buf.writeByte(a.getHairB());
        buf.writeZero(1);
        buf.writeByte(a.getMovementFlags());
        buf.writeByte(a.getEntityFlags());
        buf.writeFloat(a.getScale());
        buf.writeFloat(a.getBoundingRadius());
        buf.writeFloat(a.getBoundingHeight());
        buf.writeShort(a.getHeadModel());
        buf.writeShort(a.getHairModel());
        buf.writeShort(a.getHandModel());
        buf.writeShort(a.getFootModel());
        buf.writeShort(a.getBodyModel());
        buf.writeShort(a.getBackModel());
        buf.writeShort(a.getShoulderModel());
        buf.writeShort(a.getWingModel());
        buf.writeFloat(a.getHeadScale());
        buf.writeFloat(a.getBodyScale());
        buf.writeFloat(a.getHandScale());
        buf.writeFloat(a.getFootScale());
        buf.writeFloat(a.getShoulderScale());
        buf.writeFloat(a.getWeaponScale());
        buf.writeFloat(a.getBackScale());
        buf.writeFloat(a.getUnknown());
        buf.writeFloat(a.getWingScale());
        buf.writeFloat(a.getBodyPitch());
        buf.writeFloat(a.getArmPitch());
        buf.writeFloat(a.getArmRoll());
        buf.writeFloat(a.getArmYaw());
        buf.writeFloat(a.getFeetPitch());
        buf.writeFloat(a.getWingPitch());
        buf.writeFloat(a.getBackPitch());
        GeomCodec.writeFloatVector3(buf, a.getBodyOffset());
        GeomCodec.writeFloatVector3(buf, a.getHeadOffset());
        GeomCodec.writeFloatVector3(buf, a.getHandOffset());
        GeomCodec.writeFloatVector3(buf, a.getFootOffset());
        GeomCodec.writeFloatVector3(buf, a.getBackOffset());
        GeomCodec.writeFloatVector3(buf, a.getWingOffset());
    }

    public static Particle readParticle(ByteBuf buf) {
        LongVector3 position = GeomCodec.readLongVector3(buf);
        Particle particle = new Particle(position);
        particle.setAcceleration(GeomCodec.readFloatVector3(buf));
        particle.setColorRed(buf.readFloat());
        particle.setColorBlue(buf.readFloat());
        particle.setColorGreen(buf.readFloat());
        particle.setColorAlpha(buf.readFloat());
        particle.setScale(buf.readFloat());
        particle.setCount(buf.readInt());
        particle.setType(buf.readInt());
        particle.setSpreading(buf.readFloat());
        particle.setUnknown18(buf.readInt());
        return particle;
    }

    public static void writeParticle(ByteBuf buf, Particle particle) {
        GeomCodec.writeLongVector3(buf, particle.getPosition());
        GeomCodec.writeFloatVector3(buf, particle.getAcceleration());
        buf.writeFloat(particle.getColorRed());
        buf.writeFloat(particle.getColorBlue());
        buf.writeFloat(particle.getColorGreen());
        buf.writeFloat(particle.getColorAlpha());
        buf.writeFloat(particle.getScale());
        buf.writeInt(particle.getCount());
        buf.writeInt(particle.getType());
        buf.writeFloat(particle.getSpreading());
        buf.writeInt(particle.getUnknown18());
    }
}
