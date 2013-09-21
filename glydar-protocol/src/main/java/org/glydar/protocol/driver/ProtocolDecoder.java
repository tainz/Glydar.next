package org.glydar.protocol.driver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;

public class ProtocolDecoder extends ReplayingDecoder<Void> {

    private final GlydarLogger    logger;

    public ProtocolDecoder(GlydarLogger logger) {
        this.logger = logger;
    }

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf buf, List<Object> objects) {
        buf = buf.order(ByteOrder.LITTLE_ENDIAN);
        int packetId = buf.readInt();
        PacketType type = PacketType.valueOf(packetId);
        logger.finer("Decoding packet {0}", type);

        int indexBefore = buf.readerIndex();
        Packet packet = type.createPacket(buf);
        dumpPacket(buf, type, indexBefore);

        objects.add(packet);
    }

    private void dumpPacket(ByteBuf buf, PacketType type, int indexBefore) {
        if (!logger.getJdkLogger().isLoggable(Level.FINEST)) {
            return;
        }

        byte[] dump = new byte[buf.readerIndex() - indexBefore];
        buf.getBytes(indexBefore, dump);
        logger.finest("Read {0} : {1}", type, Arrays.toString(dump));
    }
}
