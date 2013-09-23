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

    public static EntityData readEntityData(ByteBuf buf) {
        byte[] bitSetBuf = new byte[8];
        buf.readBytes(bitSetBuf);
        BitSet bitSet = BitSet.valueOf(bitSetBuf);
        EntityChanges changes = new EntityChanges(bitSet);

        EntityData data = new EntityData(changes);
        if (changes.get(EntityChange.POSITION)) {
            data.setPosition(GeomCodec.readLongVector3(buf));
        }
        if (changes.get(EntityChange.ORIENTATION)) {
            data.setOrientation(GeomCodec.readOrientation(buf));
        }
        if (changes.get(EntityChange.VELOCITY)) {
            data.setVelocity(GeomCodec.readFloatVector3(buf));
        }
        if (changes.get(EntityChange.ACCELERATION)) {
            data.setAccel(GeomCodec.readFloatVector3(buf));
        }
        if (changes.get(EntityChange.EXTRA_VELOCITY)) {
            data.setExtraVel(GeomCodec.readFloatVector3(buf));
        }
        if (changes.get(EntityChange.LOOK_PITCH)) {
            data.setLookPitch(buf.readFloat());
        }
        if (changes.get(EntityChange.PHYSICS_FLAGS)) {
            data.setPhysicsFlags(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.HOSTILE_TYPE)) {
            data.setHostileType(buf.readByte());
        }
        if (changes.get(EntityChange.ENTITY_TYPE)) {
            data.setEntityType(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.CURRENT_MODE)) {
            data.setCurrentMode(buf.readByte());
        }
        if (changes.get(EntityChange.LAST_SHOOT_TIME)) {
            data.setLastShootTime(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.HIT_COUNTER)) {
            data.setHitCounter(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.LAST_HIT_TIME)) {
            data.setLastHitTime(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.APPEARANCE)) {
            data.setApp(readAppearance(buf));
        }
        if (changes.get(EntityChange.FLAGS)) {
            data.setFlags1(buf.readByte());
            data.setFlags2(buf.readByte());
        }
        if (changes.get(EntityChange.ROLL_TIME)) {
            data.setRollTime(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.STUN_TIME)) {
            data.setStunTime(buf.readInt());
        }
        if (changes.get(EntityChange.SLOWED_TIME)) {
            data.setSlowedTime(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.MAKE_BLUE_TIME)) {
            data.setMakeBlueTime(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.SPEED_UP_TIME)) {
            data.setSpeedUpTime(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.SLOW_PATCH_TIME)) {
            data.setSlowPatchTime(buf.readFloat());
        }
        if (changes.get(EntityChange.CLASS_TYPE)) {
            data.setClassType(buf.readByte());
        }
        if (changes.get(EntityChange.SPECIALIZATION)) {
            data.setSpecialization(buf.readByte());
        }
        if (changes.get(EntityChange.CHARGED_MP)) {
            data.setChargedMP(buf.readFloat());
        }
        if (changes.get(EntityChange.NU_1_2_3)) {
            data.setNu1(buf.readUnsignedInt());
            data.setNu2(buf.readUnsignedInt());
            data.setNu3(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.NU_4_5_6)) {
            data.setNu4(buf.readUnsignedInt());
            data.setNu5(buf.readUnsignedInt());
            data.setNu6(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.RAY_HIT)) {
            data.setRayHit(GeomCodec.readFloatVector3(buf));
        }
        if (changes.get(EntityChange.HP)) {
            data.setHP(buf.readFloat());
        }
        if (changes.get(EntityChange.MP)) {
            data.setMP(buf.readFloat());
        }
        if (changes.get(EntityChange.BLOCK_POWER)) {
            data.setBlockPower(buf.readFloat());
        }
        if (changes.get(EntityChange.MULTIPLIERS)) {
            data.setMaxHPMultiplier(buf.readFloat());
            data.setShootSpeed(buf.readFloat());
            data.setDamageMultiplier(buf.readFloat());
            data.setArmorMultiplier(buf.readFloat());
            data.setResistanceMultiplier(buf.readFloat());
        }
        if (changes.get(EntityChange.NU_7)) {
            data.setNu7(buf.readByte());
        }
        if (changes.get(EntityChange.NU_8)) {
            data.setNu8(buf.readByte());
        }
        if (changes.get(EntityChange.LEVEL)) {
            data.setLevel(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.CURRENT_XP)) {
            data.setCurrentXP(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.PARENT_OWNER)) {
            data.setParentOwner(buf.readLong());
        }
        if (changes.get(EntityChange.NA_1_2)) {
            data.setNa1(buf.readUnsignedInt());
            data.setNa2(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.NA_3)) {
            data.setNa3(buf.readByte());
        }
        if (changes.get(EntityChange.NA_4)) {
            data.setNa4(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.NA_5_NU_11_12)) {
            data.setNa5(buf.readUnsignedInt());
            data.setNu11(buf.readUnsignedInt());
            data.setNu12(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.SPAWN_POSITION)) {
            data.setSpawnPosition(GeomCodec.readLongVector3(buf));
        }
        if (changes.get(EntityChange.NU_20_21_22)) {
            data.setNu20(buf.readUnsignedInt());
            data.setNu21(buf.readUnsignedInt());
            data.setNu22(buf.readUnsignedInt());
        }
        if (changes.get(EntityChange.NU_19)) {
            data.setNu19(buf.readByte());
        }
        if (changes.get(EntityChange.ITEM_DATA)) {
            data.setItemData(ItemCodec.readItem(buf));
        }
        if (changes.get(EntityChange.EQUIPMENT)) {
            Item[] equipment = data.getEquipment();
            for (int i = 0; i < 13; i++) {
                equipment[i] = ItemCodec.readItem(buf);
            }
            data.setEquipment(equipment);
        }
        if (changes.get(EntityChange.NAME)) {
            data.setName(new String(buf.readBytes(16).array(), Charsets.US_ASCII).trim());
        }
        if (changes.get(EntityChange.SKILLS)) {
            long[] skills = data.getSkills();
            for (int i = 0; i < 11; i++) {
                skills[i] = buf.readUnsignedInt();
            }
            data.setSkills(skills);
        }
        if (changes.get(EntityChange.ICE_BLOCK_FOUR)) {
            data.setIceBlockFour(buf.readUnsignedInt());
        }

        return data;
    }

    public static void writeEntityData(ByteBuf buf, EntityData e) {
        EntityChanges changes = e.getChanges();

        byte[] bitSetBytes = changes.getBitSet().toByteArray();
        buf.writeBytes(bitSetBytes);
        // BitSet/BitArray are the stupidest classes ever :(
        buf.writeZero(8 - bitSetBytes.length);

        if (changes.get(EntityChange.POSITION)) {
            GeomCodec.writeLongVector3(buf, e.getPosition());
        }
        if (changes.get(EntityChange.ORIENTATION)) {
            GeomCodec.writeOrientation(buf, e.getOrientation());
        }
        if (changes.get(EntityChange.VELOCITY)) {
            GeomCodec.writeFloatVector3(buf, e.getVelocity());
        }
        if (changes.get(EntityChange.ACCELERATION)) {
            GeomCodec.writeFloatVector3(buf, e.getAccel());
        }
        if (changes.get(EntityChange.EXTRA_VELOCITY)) {
            GeomCodec.writeFloatVector3(buf, e.getExtraVel());
        }
        if (changes.get(EntityChange.LOOK_PITCH)) {
            buf.writeFloat(e.getLookPitch());
        }
        if (changes.get(EntityChange.PHYSICS_FLAGS)) {
            buf.writeInt((int) e.getPhysicsFlags());
        }
        if (changes.get(EntityChange.HOSTILE_TYPE)) {
            buf.writeByte(e.getHostileType());
        }
        if (changes.get(EntityChange.ENTITY_TYPE)) {
            buf.writeInt((int) e.getEntityType());
        }
        if (changes.get(EntityChange.CURRENT_MODE)) {
            buf.writeByte(e.getCurrentMode());
        }
        if (changes.get(EntityChange.LAST_SHOOT_TIME)) {
            buf.writeInt((int) e.getLastShootTime());
        }
        if (changes.get(EntityChange.HIT_COUNTER)) {
            buf.writeInt((int) e.getHitCounter());
        }
        if (changes.get(EntityChange.LAST_HIT_TIME)) {
            buf.writeInt((int) e.getLastHitTime());
        }
        if (changes.get(EntityChange.APPEARANCE)) {
            writeAppearance(buf, e.getApp());
        }
        if (changes.get(EntityChange.FLAGS)) {
            buf.writeByte(e.getFlags1());
            buf.writeByte(e.getFlags2());
        }
        if (changes.get(EntityChange.ROLL_TIME)) {
            buf.writeInt((int) e.getRollTime());
        }
        if (changes.get(EntityChange.STUN_TIME)) {
            buf.writeInt(e.getStunTime());
        }
        if (changes.get(EntityChange.SLOWED_TIME)) {
            buf.writeInt((int) e.getSlowedTime());
        }
        if (changes.get(EntityChange.MAKE_BLUE_TIME)) {
            buf.writeInt((int) e.getMakeBlueTime());
        }
        if (changes.get(EntityChange.SPEED_UP_TIME)) {
            buf.writeInt((int) e.getSpeedUpTime());
        }
        if (changes.get(EntityChange.SLOW_PATCH_TIME)) {
            buf.writeFloat(e.getSlowPatchTime());
        }
        if (changes.get(EntityChange.CLASS_TYPE)) {
            buf.writeByte(e.getClassType());
        }
        if (changes.get(EntityChange.SPECIALIZATION)) {
            buf.writeByte(e.getSpecialization());
        }
        if (changes.get(EntityChange.CHARGED_MP)) {
            buf.writeFloat(e.getChargedMP());
        }
        if (changes.get(EntityChange.NU_1_2_3)) {
            buf.writeInt((int) e.getNu1());
            buf.writeInt((int) e.getNu2());
            buf.writeInt((int) e.getNu3());
        }
        if (changes.get(EntityChange.NU_4_5_6)) {
            buf.writeInt((int) e.getNu4());
            buf.writeInt((int) e.getNu5());
            buf.writeInt((int) e.getNu6());
        }
        if (changes.get(EntityChange.RAY_HIT)) {
            GeomCodec.writeFloatVector3(buf, e.getRayHit());
        }
        if (changes.get(EntityChange.HP)) {
            buf.writeFloat(e.getHP());
        }
        if (changes.get(EntityChange.MP)) {
            buf.writeFloat(e.getMP());
        }
        if (changes.get(EntityChange.BLOCK_POWER)) {
            buf.writeFloat(e.getBlockPower());
        }
        if (changes.get(EntityChange.MULTIPLIERS)) {
            buf.writeFloat(e.getMaxHPMultiplier());
            buf.writeFloat(e.getShootSpeed());
            buf.writeFloat(e.getDamageMultiplier());
            buf.writeFloat(e.getArmorMultiplier());
            buf.writeFloat(e.getResistanceMultiplier());
        }
        if (changes.get(EntityChange.NU_7)) {
            buf.writeByte(e.getNu7());
        }
        if (changes.get(EntityChange.NU_8)) {
            buf.writeByte(e.getNu8());
        }
        if (changes.get(EntityChange.LEVEL)) {
            buf.writeInt((int) e.getLevel());
        }
        if (changes.get(EntityChange.CURRENT_XP)) {
            buf.writeInt((int) e.getCurrentXP());
        }
        if (changes.get(EntityChange.PARENT_OWNER)) {
            buf.writeLong(e.getParentOwner());
        }
        if (changes.get(EntityChange.NA_1_2)) {
            buf.writeInt((int) e.getNa1());
            buf.writeInt((int) e.getNa2());
        }
        if (changes.get(EntityChange.NA_3)) {
            buf.writeByte(e.getNa3());
        }
        if (changes.get(EntityChange.NA_4)) {
            buf.writeInt((int) e.getNa4());
        }
        if (changes.get(EntityChange.NA_5_NU_11_12)) {
            buf.writeInt((int) e.getNa5());
            buf.writeInt((int) e.getNu11());
            buf.writeInt((int) e.getNu12());
        }
        if (changes.get(EntityChange.SPAWN_POSITION)) {
            GeomCodec.writeLongVector3(buf, e.getSpawnPosition());
        }
        if (changes.get(EntityChange.NU_20_21_22)) {
            buf.writeInt((int) e.getNu20());
            buf.writeInt((int) e.getNu21());
            buf.writeInt((int) e.getNu22());
        }
        if (changes.get(EntityChange.NU_19)) {
            buf.writeByte(e.getNu19());
        }
        if (changes.get(EntityChange.ITEM_DATA)) {
            ItemCodec.writeItem(buf, e.getItemData());
        }
        if (changes.get(EntityChange.EQUIPMENT)) {
            Item[] equipment = e.getEquipment();
            for (int i = 0; i < 13; i++) {
                ItemCodec.writeItem(buf, equipment[i]);
            }
        }
        if (changes.get(EntityChange.NAME)) {
            byte[] ascii = e.getName().getBytes(Charsets.US_ASCII);
            buf.writeBytes(ascii);
            buf.writeBytes(new byte[16 - e.getName().length()]);
        }
        if (changes.get(EntityChange.SKILLS)) {
            long[] skills = e.getSkills();
            for (int i = 0; i < 11; i++) {
                buf.writeInt((int) skills[i]);
            }
        }
        if (changes.get(EntityChange.ICE_BLOCK_FOUR)) {
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
