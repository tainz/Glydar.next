package org.glydar.protocol.data;

import io.netty.buffer.ByteBuf;

import org.glydar.api.geom.FloatVector3;
import org.glydar.protocol.codec.GeomCodec;

public class SoundAction {

    private final FloatVector3 position;
    private final int          soundType;
    private final float        pitch;
    private final float        volume;

    public SoundAction(ByteBuf buf) {
        this.position = GeomCodec.readFloatVector3(buf);
        this.soundType = buf.readInt();
        this.pitch = buf.readFloat();
        this.volume = buf.readFloat();
    }

    public void writeTo(ByteBuf buf) {
        GeomCodec.writeFloatVector3(buf, position);
        buf.writeInt(soundType);
        buf.writeFloat(pitch);
        buf.writeFloat(volume);
    }

    public enum SoundType {

        HIT,

        BLADE1,

        BLADE2;

        public SoundType getSoundByNumber(int i) {
            for (final SoundType st : SoundType.values()) {
                if (st.ordinal() == i) {
                    return st;
                }
            }

            return null;
        }
    }
}
