package org.glydar.protocol.driver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.logging.Level;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;

public class ProtocolEncoder extends MessageToByteEncoder<Packet> {

    private final GlydarLogger logger;

    public ProtocolEncoder(GlydarLogger logger) {
        this.logger = logger;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        out = out.order(ByteOrder.LITTLE_ENDIAN);
        logger.finer("Writing packet {0}", packet.getPacketType());
        out.writeInt(packet.getPacketType().id());

        int indexBefore = out.writerIndex();
        packet.writeTo(out);
        dumpPacket(out, packet.getPacketType(), indexBefore);
    }

    private void dumpPacket(ByteBuf out, PacketType type, int indexBefore) {
        if (!logger.getJdkLogger().isLoggable(Level.FINEST)) {
            return;
        }

        byte[] dump = new byte[out.writerIndex() - indexBefore];
        out.getBytes(indexBefore, dump);
        logger.finest("Wrote {0} : {1}", type, Arrays.toString(dump));
    }
}
