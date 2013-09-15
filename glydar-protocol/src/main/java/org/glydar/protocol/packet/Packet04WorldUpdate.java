package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteOrder;

import org.glydar.api.Glydar;
import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.data.WorldUpdateData;
import org.glydar.protocol.util.ZLibOperations;

public class Packet04WorldUpdate implements Packet {

    private final WorldUpdateData data;

    public Packet04WorldUpdate(WorldUpdateData data) {
        this.data = data;
    }

    public Packet04WorldUpdate(ByteBuf buf) {
        this.data = new WorldUpdateData(buf);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.WORLD_UPDATE;
    }

    @Override
    public void writeTo(ByteBuf buf) {
        ByteBuf buf2 = Unpooled.buffer();
        buf2 = buf2.order(ByteOrder.LITTLE_ENDIAN);
        data.writeTo(buf2);
        byte[] compressedData = null;
        try {
            compressedData = ZLibOperations.compress(buf2.array());
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        if (compressedData != null) {
            buf.writeInt(compressedData.length);
            buf.writeBytes(compressedData);
        }
        else {
            Glydar.getLogger().severe("World update is null! o.o");
        }
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
