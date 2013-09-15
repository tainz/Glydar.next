package org.glydar.protocol.data;

import io.netty.buffer.ByteBuf;

import java.util.BitSet;

import org.glydar.api.entity.Appearance;
import org.glydar.api.entity.EntityChange;
import org.glydar.api.entity.EntityChanges;
import org.glydar.api.entity.EntityData;
import org.glydar.api.geom.FloatVector3;
import org.glydar.api.geom.LongVector3;
import org.glydar.api.geom.Orientation;
import org.glydar.api.item.Item;
import org.glydar.api.item.ItemUpgrade;

import com.google.common.base.Charsets;

public final class DataCodec {

    public static EntityChanges readEntityChanges(ByteBuf buf) {
        byte[] bitSetBuf = new byte[8];
        buf.readBytes(bitSetBuf);
        BitSet bitSet = BitSet.valueOf(bitSetBuf);
        return new EntityChanges(bitSet);
    }

    public static void writeEntityChanges(ByteBuf buf, EntityChanges changes) {
        buf.writeBytes(changes.toByteArray());
    }

    public static EntityData readEntityData(ByteBuf buf, EntityChanges bitSet) {
        EntityData data = new EntityData();

        if (bitSet.get(EntityChange.POSITION)) {
            data.setPosition(readLongVector3(buf));
        }
        if (bitSet.get(EntityChange.ORIENTATION)) {
            data.setOrientation(readOrientation(buf));
        }
        if (bitSet.get(EntityChange.VELOCITY)) {
            data.setVelocity(readFloatVector3(buf));
        }
        if (bitSet.get(EntityChange.ACCELERATION)) {
            data.setAccel(readFloatVector3(buf));
        }
        if (bitSet.get(EntityChange.EXTRA_VELOCITY)) {
            data.setExtraVel(readFloatVector3(buf));
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
            data.setRayHit(readFloatVector3(buf));
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
            data.setSpawnPosition(readLongVector3(buf));
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
            data.setItemData(readItem(buf));
        }
        if (bitSet.get(EntityChange.EQUIPMENT)) {
            Item[] equipment = data.getEquipment();
            for (int i = 0; i < 13; i++) {
                equipment[i] = readItem(buf);
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

    public static void writeEntityData(ByteBuf buf, EntityChanges changes, EntityData data) {
        if (changes.get(EntityChange.POSITION)) {
            writeLongVector3(buf, data.getPosition());
        }
        if (changes.get(EntityChange.ORIENTATION)) {
            writeOrientation(buf, data.getOrientation());
        }
        if (changes.get(EntityChange.VELOCITY)) {
            writeFloatVector3(buf, data.getVelocity());
        }
        if (changes.get(EntityChange.ACCELERATION)) {
            writeFloatVector3(buf, data.getAccel());
        }
        if (changes.get(EntityChange.EXTRA_VELOCITY)) {
            writeFloatVector3(buf, data.getExtraVel());
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
            writeFloatVector3(buf, data.getRayHit());
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
            writeLongVector3(buf, data.getSpawnPosition());
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
            writeItem(buf, data.getItemData());
        }
        if (changes.get(EntityChange.EQUIPMENT)) {
            Item[] equipment = data.getEquipment();
            for (int i = 0; i < 13; i++) {
                writeItem(buf, equipment[i]);
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
        a.setBodyOffset(readFloatVector3(buf));
        a.setHeadOffset(readFloatVector3(buf));
        a.setHandOffset(readFloatVector3(buf));
        a.setFootOffset(readFloatVector3(buf));
        a.setBackOffset(readFloatVector3(buf));
        a.setWingOffset(readFloatVector3(buf));
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
        writeFloatVector3(buf, a.getBodyOffset());
        writeFloatVector3(buf, a.getHeadOffset());
        writeFloatVector3(buf, a.getHandOffset());
        writeFloatVector3(buf, a.getFootOffset());
        writeFloatVector3(buf, a.getBackOffset());
        writeFloatVector3(buf, a.getWingOffset());
    }

    public static Item readItem(ByteBuf buf) {
        Item i = new Item();
        i.setType(buf.readByte());
        i.setSubtype(buf.readByte());
        buf.skipBytes(2);
        i.setModifier(buf.readUnsignedInt());
        i.setMinusModifier(buf.readUnsignedInt());
        i.setRarity(buf.readByte());
        i.setMaterial(buf.readByte());
        i.setFlags(buf.readByte());
        buf.skipBytes(1);
        i.setLevel(buf.readShort());
        buf.skipBytes(2);

        ItemUpgrade[] upgrades = i.getUpgrades();
        for (int j = 0; j < upgrades.length; ++j) {
            upgrades[j] = readItemUpgrade(buf);
        }
        i.setUpgrades(upgrades);

        i.setUpgradeCount(buf.readUnsignedInt());
        return i;
    }

    public static void writeItem(ByteBuf buf, Item i) {
        buf.writeByte(i.getType());
        buf.writeByte(i.getSubtype());
        buf.writeZero(2);
        buf.writeInt((int) i.getModifier());
        buf.writeInt((int) i.getMinusModifier());
        buf.writeByte(i.getRarity());
        buf.writeByte(i.getMaterial());
        buf.writeByte(i.getFlags());
        buf.writeZero(1);
        buf.writeShort(i.getLevel());
        buf.writeZero(2);
        ItemUpgrade[] upgrades = i.getUpgrades();
        for (int j = 0; j < upgrades.length; ++j) {
            writeItemUpgrade(buf, upgrades[j]);
        }
        buf.writeInt((int) i.getUpgradeCount());
    }

    public static ItemUpgrade readItemUpgrade(ByteBuf buf) {
        ItemUpgrade u = new ItemUpgrade();
        u.setxOffset(buf.readByte());
        u.setyOffset(buf.readByte());
        u.setzOffset(buf.readByte());
        u.setMaterial(buf.readByte());
        u.setLevel(buf.readUnsignedInt());
        return u;
    }

    public static void writeItemUpgrade(ByteBuf buf, ItemUpgrade u) {
        buf.writeByte(u.getxOffset());
        buf.writeByte(u.getyOffset());
        buf.writeByte(u.getzOffset());
        buf.writeByte(u.getMaterial());
        buf.writeInt((int) u.getLevel());
    }

    public static FloatVector3 readFloatVector3(ByteBuf buf) {
        return new FloatVector3(buf.readFloat(), buf.readFloat(), buf.readFloat());
    }

    public static LongVector3 readLongVector3(ByteBuf buf) {
        return new LongVector3(buf.readLong(), buf.readLong(), buf.readLong());
    }

    public static Orientation readOrientation(ByteBuf buf) {
        return new Orientation(buf.readFloat(), buf.readFloat(), buf.readFloat());
    }

    public static void writeFloatVector3(ByteBuf buf, FloatVector3 vector) {
        buf.writeFloat(vector.getX());
        buf.writeFloat(vector.getY());
        buf.writeFloat(vector.getZ());
    }

    public static void writeLongVector3(ByteBuf buf, LongVector3 vector) {
        buf.writeLong(vector.getX());
        buf.writeLong(vector.getY());
        buf.writeLong(vector.getZ());
    }

    public static void writeOrientation(ByteBuf buf, Orientation orientation) {
        buf.writeFloat(orientation.getRoll());
        buf.writeFloat(orientation.getPitch());
        buf.writeFloat(orientation.getYaw());
    }
}
