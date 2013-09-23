package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteOrder;
import java.util.Arrays;

import org.glydar.api.Glydar;
import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.RemoteType;
import org.glydar.protocol.codec.WorldUpdates;
import org.glydar.protocol.util.ZLibOperations;

public class Packet04WorldUpdate implements Packet {

    private final byte[] rawData;

    // private final WorldUpdateData data;

    public Packet04WorldUpdate(ByteBuf buf) {
        int length = buf.readInt();
        this.rawData = new byte[length];
        buf.readBytes(rawData);

        if (length > 12) {
            try {
                byte[] decompressedBytes = ZLibOperations.decompressBytes(rawData);
                Glydar.getLogger().info("Decompressed : {0}", Arrays.toString(decompressedBytes));

                ByteBuf decompressedBuf = Unpooled.copiedBuffer(decompressedBytes);
                decompressedBuf = decompressedBuf.order(ByteOrder.LITTLE_ENDIAN);
                WorldUpdates worldUpdate = new WorldUpdates(decompressedBuf);
                ByteBuf writtenBuf = Unpooled.buffer();
                writtenBuf = writtenBuf.order(ByteOrder.LITTLE_ENDIAN);
                worldUpdate.writeTo(RemoteType.CLIENT, writtenBuf);

                byte[] writtenBytes = new byte[writtenBuf.readableBytes()];
                writtenBuf.readBytes(writtenBytes);
                Glydar.getLogger().info("Written      : {0}", Arrays.toString(writtenBytes));
            }
            catch (Exception exc) {
                Glydar.getLogger().warning(exc, "Error while decoding packet 4");
            }
        }
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.WORLD_UPDATE;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        buf.writeInt(rawData.length);
        buf.writeBytes(rawData);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
