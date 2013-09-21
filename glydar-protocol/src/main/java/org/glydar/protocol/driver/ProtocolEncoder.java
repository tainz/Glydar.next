package org.glydar.protocol.driver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.logging.Level;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;

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
