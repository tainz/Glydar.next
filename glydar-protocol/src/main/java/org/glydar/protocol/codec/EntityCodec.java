package org.glydar.protocol.codec;

import io.netty.buffer.ByteBuf;

import java.util.BitSet;

import org.glydar.api.entity.Appearance;
import org.glydar.api.entity.EntityChange;
import org.glydar.api.entity.EntityChanges;
import org.glydar.api.entity.EntityData;
import org.glydar.api.entity.Particle;
import org.glydar.api.geom.LongVector3;
import org.glydar.api.item.Item;

import com.google.common.base.Charsets;

public final class EntityCodec {

    private EntityCodec() {
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

    public static EntityChanges readEntityChanges(ByteBuf buf) {
        byte[] bitSetBuf = new byte[8];
        buf.readBytes(bitSetBuf);
        BitSet bitSet = BitSet.valueOf(bitSetBuf);
        return new EntityChanges(bitSet);
    }

    public static EntityData readEntityData(ByteBuf buf, EntityChanges bitSet) {
        EntityData data = new EntityData();

        if (bitSet.get(EntityChange.POSITION)) {
            data.setPosition(GeomCodec.readLongVector3(buf));
        }
        if (bitSet.get(EntityChange.ORIENTATION)) {
            data.setOrientation(GeomCodec.readOrientation(buf));
        }
        if (bitSet.get(EntityChange.VELOCITY)) {
            data.setVelocity(GeomCodec.readFloatVector3(buf));
        }
        if (bitSet.get(EntityChange.ACCELERATION)) {
            data.setAccel(GeomCodec.readFloatVector3(buf));
        }
        if (bitSet.get(EntityChange.EXTRA_VELOCITY)) {
            data.setExtraVel(GeomCodec.readFloatVector3(buf));
        }
        if (bitSet.get(EntityChange.LOOK_PITCH)) {
            data.setLookPitch(buf.readFloat());
        }
        if (bitSet.get(EntityChange.PHYSICS_FLAGS)) {
            data.setPhysicsFlags(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.HOSTILE_TYPE)) {
            data.setHostileType(buf.readByte());
        }
        if (bitSet.get(EntityChange.ENTITY_TYPE)) {
            data.setEntityType(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.CURRENT_MODE)) {
            data.setCurrentMode(buf.readByte());
        }
        if (bitSet.get(EntityChange.LAST_SHOOT_TIME)) {
            data.setLastShootTime(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.HIT_COUNTER)) {
            data.setHitCounter(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.LAST_HIT_TIME)) {
            data.setLastHitTime(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.APPEARANCE)) {
            data.setApp(readAppearance(buf));
        }
        if (bitSet.get(EntityChange.FLAGS)) {
            data.setFlags1(buf.readByte());
            data.setFlags2(buf.readByte());
        }
        if (bitSet.get(EntityChange.ROLL_TIME)) {
            data.setRollTime(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.STUN_TIME)) {
            data.setStunTime(buf.readInt());
        }
        if (bitSet.get(EntityChange.SLOWED_TIME)) {
            data.setSlowedTime(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.MAKE_BLUE_TIME)) {
            data.setMakeBlueTime(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.SPEED_UP_TIME)) {
            data.setSpeedUpTime(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.SLOW_PATCH_TIME)) {
            data.setSlowPatchTime(buf.readFloat());
        }
        if (bitSet.get(EntityChange.CLASS_TYPE)) {
            data.setClassType(buf.readByte());
        }
        if (bitSet.get(EntityChange.SPECIALIZATION)) {
            data.setSpecialization(buf.readByte());
        }
        if (bitSet.get(EntityChange.CHARGED_MP)) {
            data.setChargedMP(buf.readFloat());
        }
        if (bitSet.get(EntityChange.NU_1_2_3)) {
            data.setNu1(buf.readUnsignedInt());
            data.setNu2(buf.readUnsignedInt());
            data.setNu3(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.NU_4_5_6)) {
            data.setNu4(buf.readUnsignedInt());
            data.setNu5(buf.readUnsignedInt());
            data.setNu6(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.RAY_HIT)) {
            data.setRayHit(GeomCodec.readFloatVector3(buf));
        }
        if (bitSet.get(EntityChange.HP)) {
            data.setHp(buf.readFloat());
        }
        if (bitSet.get(EntityChange.MP)) {
            data.setMp(buf.readFloat());
        }
        if (bitSet.get(EntityChange.BLOCK_POWER)) {
            data.setBlockPower(buf.readFloat());
        }
        if (bitSet.get(EntityChange.MULTIPLIERS)) {
            data.setMaxHPMultiplier(buf.readFloat());
            data.setShootSpeed(buf.readFloat());
            data.setDamageMultiplier(buf.readFloat());
            data.setArmorMultiplier(buf.readFloat());
            data.setResistanceMultiplier(buf.readFloat());
        }
        if (bitSet.get(EntityChange.NU_7)) {
            data.setNu7(buf.readByte());
        }
        if (bitSet.get(EntityChange.NU_8)) {
            data.setNu8(buf.readByte());
        }
        if (bitSet.get(EntityChange.LEVEL)) {
            data.setLevel(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.CURRENT_XP)) {
            data.setCurrentXP(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.PARENT_OWNER)) {
            data.setParentOwner(buf.readLong());
        }
        if (bitSet.get(EntityChange.NA_1_2)) {
            data.setNa1(buf.readUnsignedInt());
            data.setNa2(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.NA_3)) {
            data.setNa3(buf.readByte());
        }
        if (bitSet.get(EntityChange.NA_4)) {
            data.setNa4(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.NA_5_NU_11_12)) {
            data.setNa5(buf.readUnsignedInt());
            data.setNu11(buf.readUnsignedInt());
            data.setNu12(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.SPAWN_POSITION)) {
            data.setSpawnPosition(GeomCodec.readLongVector3(buf));
        }
        if (bitSet.get(EntityChange.NU_20_21_22)) {
            data.setNu20(buf.readUnsignedInt());
            data.setNu21(buf.readUnsignedInt());
            data.setNu22(buf.readUnsignedInt());
        }
        if (bitSet.get(EntityChange.NU_19)) {
            data.setNu19(buf.readByte());
        }
        if (bitSet.get(EntityChange.ITEM_DATA)) {
            data.setItemData(ItemCodec.readItem(buf));
        }
        if (bitSet.get(EntityChange.EQUIPMENT)) {
            Item[] equipment = data.getEquipment();
            for (int i = 0; i < 13; i++) {
                equipment[i] = ItemCodec.readItem(buf);
            }
            data.setEquipment(equipment);
        }
        if (bitSet.get(EntityChange.NAME)) {
            data.setName(new String(buf.readBytes(16).array(), Charsets.US_ASCII).trim());
        }
        if (bitSet.get(EntityChange.SKILLS)) {
            long[] skills = data.getSkills();
            for (int i = 0; i < 11; i++) {
                skills[i] = buf.readUnsignedInt();
            }
            data.setSkills(skills);
        }
        if (bitSet.get(EntityChange.ICE_BLOCK_FOUR)) {
            data.setIceBlockFour(buf.readUnsignedInt());
        }

        return data;
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

    public static void writeEntityChanges(ByteBuf buf, EntityChanges changes) {
        buf.writeBytes(changes.toByteArray());
    }

    public static void writeEntityData(ByteBuf buf, EntityChanges changes, EntityData data) {
        if (changes.get(EntityChange.POSITION)) {
            GeomCodec.writeLongVector3(buf, data.getPosition());
        }
        if (changes.get(EntityChange.ORIENTATION)) {
            GeomCodec.writeOrientation(buf, data.getOrientation());
        }
        if (changes.get(EntityChange.VELOCITY)) {
            GeomCodec.writeFloatVector3(buf, data.getVelocity());
        }
        if (changes.get(EntityChange.ACCELERATION)) {
            GeomCodec.writeFloatVector3(buf, data.getAccel());
        }
        if (changes.get(EntityChange.EXTRA_VELOCITY)) {
            GeomCodec.writeFloatVector3(buf, data.getExtraVel());
        }
        if (changes.get(EntityChange.LOOK_PITCH)) {
            buf.writeFloat(data.getLookPitch());
        }
        if (changes.get(EntityChange.PHYSICS_FLAGS)) {
            buf.writeInt((int) data.getPhysicsFlags());
        }
        if (changes.get(EntityChange.HOSTILE_TYPE)) {
            buf.writeByte(data.getHostileType());
        }
        if (changes.get(EntityChange.ENTITY_TYPE)) {
            buf.writeInt((int) data.getEntityType());
        }
        if (changes.get(EntityChange.CURRENT_MODE)) {
            buf.writeByte(data.getCurrentMode());
        }
        if (changes.get(EntityChange.LAST_SHOOT_TIME)) {
            buf.writeInt((int) data.getLastShootTime());
        }
        if (changes.get(EntityChange.HIT_COUNTER)) {
            buf.writeInt((int) data.getHitCounter());
        }
        if (changes.get(EntityChange.LAST_HIT_TIME)) {
            buf.writeInt((int) data.getLastHitTime());
        }
        if (changes.get(EntityChange.APPEARANCE)) {
            writeAppearance(buf, data.getApp());
        }
        if (changes.get(EntityChange.FLAGS)) {
            buf.writeByte(data.getFlags1());
            buf.writeByte(data.getFlags2());
        }
        if (changes.get(EntityChange.ROLL_TIME)) {
            buf.writeInt((int) data.getRollTime());
        }
        if (changes.get(EntityChange.STUN_TIME)) {
            buf.writeInt(data.getStunTime());
        }
        if (changes.get(EntityChange.SLOWED_TIME)) {
            buf.writeInt((int) data.getSlowedTime());
        }
        if (changes.get(EntityChange.MAKE_BLUE_TIME)) {
            buf.writeInt((int) data.getMakeBlueTime());
        }
        if (changes.get(EntityChange.SPEED_UP_TIME)) {
            buf.writeInt((int) data.getSpeedUpTime());
        }
        if (changes.get(EntityChange.SLOW_PATCH_TIME)) {
            buf.writeFloat(data.getSlowPatchTime());
        }
        if (changes.get(EntityChange.CLASS_TYPE)) {
            buf.writeByte(data.getClassType());
        }
        if (changes.get(EntityChange.SPECIALIZATION)) {
            buf.writeByte(data.getSpecialization());
        }
        if (changes.get(EntityChange.CHARGED_MP)) {
            buf.writeFloat(data.getChargedMP());
        }
        if (changes.get(EntityChange.NU_1_2_3)) {
            buf.writeInt((int) data.getNu1());
            buf.writeInt((int) data.getNu2());
            buf.writeInt((int) data.getNu3());
        }
        if (changes.get(EntityChange.NU_4_5_6)) {
            buf.writeInt((int) data.getNu4());
            buf.writeInt((int) data.getNu5());
            buf.writeInt((int) data.getNu6());
        }
        if (changes.get(EntityChange.RAY_HIT)) {
            GeomCodec.writeFloatVector3(buf, data.getRayHit());
        }
        if (changes.get(EntityChange.HP)) {
            buf.writeFloat(data.getHp());
        }
        if (changes.get(EntityChange.MP)) {
            buf.writeFloat(data.getMp());
        }
        if (changes.get(EntityChange.BLOCK_POWER)) {
            buf.writeFloat(data.getBlockPower());
        }
        if (changes.get(EntityChange.MULTIPLIERS)) {
            buf.writeFloat(data.getMaxHPMultiplier());
            buf.writeFloat(data.getShootSpeed());
            buf.writeFloat(data.getDamageMultiplier());
            buf.writeFloat(data.getArmorMultiplier());
            buf.writeFloat(data.getResistanceMultiplier());
        }
        if (changes.get(EntityChange.NU_7)) {
            buf.writeByte(data.getNu7());
        }
        if (changes.get(EntityChange.NU_8)) {
            buf.writeByte(data.getNu8());
        }
        if (changes.get(EntityChange.LEVEL)) {
            buf.writeInt((int) data.getLevel());
        }
        if (changes.get(EntityChange.CURRENT_XP)) {
            buf.writeInt((int) data.getCurrentXP());
        }
        if (changes.get(EntityChange.PARENT_OWNER)) {
            buf.writeLong(data.getParentOwner());
        }
        if (changes.get(EntityChange.NA_1_2)) {
            buf.writeInt((int) data.getNa1());
            buf.writeInt((int) data.getNa2());
        }
        if (changes.get(EntityChange.NA_3)) {
            buf.writeByte(data.getNa3());
        }
        if (changes.get(EntityChange.NA_4)) {
            buf.writeInt((int) data.getNa4());
        }
        if (changes.get(EntityChange.NA_5_NU_11_12)) {
            buf.writeInt((int) data.getNa5());
            buf.writeInt((int) data.getNu11());
            buf.writeInt((int) data.getNu12());
        }
        if (changes.get(EntityChange.SPAWN_POSITION)) {
            GeomCodec.writeLongVector3(buf, data.getSpawnPosition());
        }
        if (changes.get(EntityChange.NU_20_21_22)) {
            buf.writeInt((int) data.getNu20());
            buf.writeInt((int) data.getNu21());
            buf.writeInt((int) data.getNu22());
        }
        if (changes.get(EntityChange.NU_19)) {
            buf.writeByte(data.getNu19());
        }
        if (changes.get(EntityChange.ITEM_DATA)) {
            ItemCodec.writeItem(buf, data.getItemData());
        }
        if (changes.get(EntityChange.EQUIPMENT)) {
            Item[] equipment = data.getEquipment();
            for (int i = 0; i < 13; i++) {
                ItemCodec.writeItem(buf, equipment[i]);
            }
        }
        if (changes.get(EntityChange.NAME)) {
            byte[] ascii = data.getName().getBytes(Charsets.US_ASCII);
            buf.writeBytes(ascii);
            buf.writeZero(16 - data.getName().length());
        }
        if (changes.get(EntityChange.SKILLS)) {
            long[] skills = data.getSkills();
            for (int i = 0; i < 11; i++) {
                buf.writeInt((int) skills[i]);
            }
        }
        if (changes.get(EntityChange.ICE_BLOCK_FOUR)) {
            buf.writeInt((int) data.getIceBlockFour());
        }
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
