package org.glydar.core.protocol.codec;

import io.netty.buffer.ByteBuf;

import org.glydar.core.model.geom.FloatVector3;
import org.glydar.core.model.geom.IntVector2;
import org.glydar.core.model.geom.IntVector3;
import org.glydar.core.model.geom.LongVector3;
import org.glydar.core.model.geom.Orientation;

public final class GeomCodec {

    private GeomCodec() {
    }

    public static IntVector2 readIntVector2(ByteBuf buf) {
        return new IntVector2(buf.readInt(), buf.readInt());
    }

    public static void writeIntVector2(ByteBuf buf, IntVector2 vector) {
        buf.writeInt(vector.getX());
        buf.writeInt(vector.getY());
    }

    public static IntVector3 readIntVector3(ByteBuf buf) {
        return new IntVector3(buf.readInt(), buf.readInt(), buf.readInt());
    }

    public static void writeIntVector3(ByteBuf buf, IntVector3 vector) {
        buf.writeInt(vector.getX());
        buf.writeInt(vector.getY());
        buf.writeInt(vector.getZ());
    }

    public static LongVector3 readLongVector3(ByteBuf buf) {
        return new LongVector3(buf.readLong(), buf.readLong(), buf.readLong());
    }

    public static void writeLongVector3(ByteBuf buf, LongVector3 vector) {
        buf.writeLong(vector.getX());
        buf.writeLong(vector.getY());
        buf.writeLong(vector.getZ());
    }

    public static FloatVector3 readFloatVector3(ByteBuf buf) {
        return new FloatVector3(buf.readFloat(), buf.readFloat(), buf.readFloat());
    }

    public static void writeFloatVector3(ByteBuf buf, FloatVector3 vector) {
        buf.writeFloat(vector.getX());
        buf.writeFloat(vector.getY());
        buf.writeFloat(vector.getZ());
    }

    public static Orientation readOrientation(ByteBuf buf) {
        return new Orientation(buf.readFloat(), buf.readFloat(), buf.readFloat());
    }

    public static void writeOrientation(ByteBuf buf, Orientation orientation) {
        buf.writeFloat(orientation.getRoll());
        buf.writeFloat(orientation.getPitch());
        buf.writeFloat(orientation.getYaw());
    }
}
