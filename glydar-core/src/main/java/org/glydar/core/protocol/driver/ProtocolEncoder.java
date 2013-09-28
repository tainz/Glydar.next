package org.glydar.core.protocol.driver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.logging.Level;

import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;

public class ProtocolEncoder<T extends Remote> extends MessageToByteEncoder<Packet> {

    private final ProtocolHandler<T> handler;

    public ProtocolEncoder(ProtocolHandler<T> handler) {
        this.handler = handler;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        out = out.order(ByteOrder.LITTLE_ENDIAN);
        handler.getLogger().finer("Writing packet {0}", packet.getPacketType());
        out.writeInt(packet.getPacketType().id());

        int indexBefore = out.writerIndex();
        packet.writeTo(handler.getRemoteType(), out);
        dumpPacket(out, packet.getPacketType(), indexBefore);
    }

    private void dumpPacket(ByteBuf out, PacketType type, int indexBefore) {
        if (!handler.getLogger().getJdkLogger().isLoggable(Level.FINEST)) {
            return;
        }

        byte[] dump = new byte[out.writerIndex() - indexBefore];
        out.getBytes(indexBefore, dump);
        handler.getLogger().finest("Wrote {0} : {1}", type, Arrays.toString(dump));
    }
}
