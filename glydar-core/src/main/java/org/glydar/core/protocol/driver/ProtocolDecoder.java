package org.glydar.core.protocol.driver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;

public class ProtocolDecoder<T extends Remote> extends ReplayingDecoder<Void> {

    private final ProtocolHandler<T> handler;

    public ProtocolDecoder(ProtocolHandler<T> handler) {
        this.handler = handler;
    }

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf buf, List<Object> objects) {
        buf = buf.order(ByteOrder.LITTLE_ENDIAN);
        int packetId = buf.readInt();
        PacketType type = PacketType.valueOf(packetId);
        handler.getLogger().finer("Decoding packet {0}", type);

        int indexBefore = buf.readerIndex();
        Packet packet = type.createPacket(handler.getRemoteType(), buf);
        dumpPacket(buf, type, indexBefore);

        objects.add(packet);
    }

    private void dumpPacket(ByteBuf buf, PacketType type, int indexBefore) {
        if (!handler.getLogger().getJdkLogger().isLoggable(Level.FINEST)) {
            return;
        }

        byte[] dump = new byte[buf.readerIndex() - indexBefore];
        buf.getBytes(indexBefore, dump);
        handler.getLogger().finest("Read {0} : {1}", type, Arrays.toString(dump));
    }
}
