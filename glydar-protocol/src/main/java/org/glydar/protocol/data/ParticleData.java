package org.glydar.protocol.data;

import io.netty.buffer.ByteBuf;

import org.glydar.api.geom.FloatVector3;
import org.glydar.api.geom.LongVector3;
import org.glydar.protocol.codec.GeomCodec;

public class ParticleData {

    private final LongVector3  position;
    private final FloatVector3 acceleration;
    private final float        colorRed;
    private final float        colorBlue;
    private final float        colorGreen;
    private final float        colorAlpha;
    private final float        scale;
    private final int          count;
    private final int          type;
    private final float        spreading;
    private final int          something18;

    public ParticleData(ByteBuf buf) {
        this.position = GeomCodec.readLongVector3(buf);
        this.acceleration = GeomCodec.readFloatVector3(buf);
        this.colorRed = buf.readFloat();
        this.colorBlue = buf.readFloat();
        this.colorGreen = buf.readFloat();
        this.colorAlpha = buf.readFloat();
        this.scale = buf.readFloat();
        this.count = buf.readInt();
        this.type = buf.readInt();
        this.spreading = buf.readFloat();
        this.something18 = buf.readInt();
    }

    public void writeTo(ByteBuf buf) {
        GeomCodec.writeLongVector3(buf, position);
        GeomCodec.writeFloatVector3(buf, acceleration);
        buf.writeFloat(colorRed);
        buf.writeFloat(colorBlue);
        buf.writeFloat(colorGreen);
        buf.writeFloat(colorAlpha);
        buf.writeFloat(scale);
        buf.writeInt(count);
        buf.writeInt(type);
        buf.writeFloat(spreading);
        buf.writeInt(something18);
    }
}
