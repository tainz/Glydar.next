package org.glydar.protocol.data;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import org.glydar.protocol.packet.Packet07Hit;
import org.glydar.protocol.packet.Packet09Shoot;
import org.glydar.protocol.packet.Packet13MissionData;

import com.google.common.base.Objects;

public class WorldUpdateData {

    private final List<Unknown1Data>        unknown1List;
    private final List<Packet07Hit>         hitPackets;
    private final List<ParticleData>        particles;
    private final List<SoundAction>         soundActions;
    private final List<Packet09Shoot>       shootPackets;
    private final List<Unknown6Data>        unknown6List;
    private final List<ChunkItems>          chunkItems;
    private final List<Unknown8Data>        unknown8List;
    private final List<PickupAction>        pickupActions;
    private final List<KillAction>          killActions;
    private final List<DamageAction>        damageActions;
    private final List<Unknown12Data>       unknown12List;
    private final List<Packet13MissionData> missions;

    public WorldUpdateData() {
        this.unknown1List = new ArrayList<>();
        this.hitPackets = new ArrayList<>();
        this.particles = new ArrayList<>();
        this.soundActions = new ArrayList<>();
        this.shootPackets = new ArrayList<>();
        this.unknown6List = new ArrayList<>();
        this.chunkItems = new ArrayList<>();
        this.unknown8List = new ArrayList<>();
        this.pickupActions = new ArrayList<>();
        this.killActions = new ArrayList<>();
        this.damageActions = new ArrayList<>();
        this.unknown12List = new ArrayList<>();
        this.missions = new ArrayList<>();
    }

    public WorldUpdateData(ByteBuf buf) {
        this();
        int length;

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            unknown1List.add(new Unknown1Data(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            hitPackets.add(new Packet07Hit(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            particles.add(new ParticleData(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            soundActions.add(new SoundAction(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            shootPackets.add(new Packet09Shoot(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            unknown6List.add(new Unknown6Data(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            chunkItems.add(new ChunkItems(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            unknown8List.add(new Unknown8Data(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            pickupActions.add(new PickupAction(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            killActions.add(new KillAction(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            damageActions.add(new DamageAction(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            unknown12List.add(new Unknown12Data(buf));
        }

        length = buf.readInt();
        for (int i = 0; i < length; i++) {
            missions.add(new Packet13MissionData(buf));
        }
    }

    public void writeTo(ByteBuf buf) {
        buf.writeInt(unknown1List.size());
        for (Unknown1Data u : unknown1List) {
            u.writeTo(buf);
        }

        buf.writeInt(hitPackets.size());
        for (Packet07Hit packet : hitPackets) {
            packet.writeTo(buf);
        }

        buf.writeInt(particles.size());
        for (ParticleData particle : particles) {
            particle.writeTo(buf);
        }

        buf.writeInt(soundActions.size());
        for (SoundAction a : soundActions) {
            a.writeTo(buf);
        }

        buf.writeInt(shootPackets.size());
        for (Packet09Shoot packets : shootPackets) {
            packets.writeTo(buf);
        }

        buf.writeInt(unknown6List.size());
        for (Unknown6Data unknown6 : unknown6List) {
            unknown6.writeTo(buf);
        }

        buf.writeInt(chunkItems.size());
        for (ChunkItems c : chunkItems) {
            c.writeTo(buf);
        }

        buf.writeInt(unknown8List.size());
        for (Unknown8Data unknown8 : unknown8List) {
            unknown8.writeTo(buf);
        }

        buf.writeInt(pickupActions.size());
        for (PickupAction a : pickupActions) {
            a.writeTo(buf);
        }

        buf.writeInt(killActions.size());
        for (KillAction a : killActions) {
            a.writeTo(buf);
        }

        buf.writeInt(damageActions.size());
        for (DamageAction a : damageActions) {
            a.writeTo(buf);
        }

        buf.writeInt(unknown12List.size());
        for (Unknown12Data unknown12 : unknown12List) {
            unknown12.writeTo(buf);
        }

        buf.writeInt(missions.size());
        for (Packet13MissionData p : missions) {
            p.writeTo(buf);
        }
    }
}

class Unknown1Data {

    private final int   blockX;
    private final int   blockY;
    private final int   blockZ;
    private final short colorRed;
    private final short colorGreen;
    private final short colorBlue;
    private final short blockType;
    private final long  unknown8;

    public Unknown1Data(ByteBuf buf) {
        this.blockX = buf.readInt();
        this.blockY = buf.readInt();
        this.blockZ = buf.readInt();
        this.colorRed = buf.readUnsignedByte();
        this.colorGreen = buf.readUnsignedByte();
        this.colorBlue = buf.readUnsignedByte();
        this.blockType = buf.readUnsignedByte();
        this.unknown8 = buf.readUnsignedInt();
    }

    public void writeTo(ByteBuf buf) {
        buf.writeInt(blockX);
        buf.writeInt(blockY);
        buf.writeInt(blockZ);
        buf.writeByte(colorRed);
        buf.writeByte(colorGreen);
        buf.writeByte(colorBlue);
        buf.writeByte(blockType);
        buf.writeInt((int) unknown8);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(getClass()).add("blockX", blockX).add("blockY", blockY).add("blockZ", blockZ)
                .add("colorRed", colorRed).add("colorGreen", colorGreen).add("colorBlue", colorBlue)
                .add("blockType", blockType).add("unknown8", unknown8).toString();
    }
}

class Unknown6Data {

    private final byte[] bytes;

    public Unknown6Data(ByteBuf buf) {
        this.bytes = new byte[88];
        buf.readBytes(bytes);
    }

    public void writeTo(ByteBuf buf) {
        buf.writeBytes(bytes);
    }
}

class Unknown8Data {

    private final long              unknown1;
    private final ArrayList<byte[]> bytesList;

    public Unknown8Data(ByteBuf buf) {
        this.unknown1 = buf.readLong();
        this.bytesList = new ArrayList<byte[]>();

        int length = buf.readInt();
        for (int i = 0; i < length; i++) {
            byte[] bytes = new byte[16];
            buf.readBytes(bytes);
            bytesList.add(bytes);
        }
    }

    public void writeTo(ByteBuf buf) {
        buf.writeLong(unknown1);
        buf.writeInt(bytesList.size());
        for (final byte[] bytes : bytesList) {
            buf.writeBytes(bytes);
        }
    }
}

class Unknown12Data {

    private final byte[] bytes;

    public Unknown12Data(ByteBuf buf) {
        this.bytes = new byte[88];
        buf.readBytes(bytes);
    }

    public void writeTo(ByteBuf buf) {
        buf.writeBytes(bytes);
    }
}
