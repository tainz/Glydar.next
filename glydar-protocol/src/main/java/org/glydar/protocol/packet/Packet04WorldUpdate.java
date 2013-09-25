package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.RemoteType;
import org.glydar.protocol.codec.WorldUpdates;
import org.glydar.protocol.util.ZLibOperations;

public class Packet04WorldUpdate implements Packet {

    private final WorldUpdates data;

    public Packet04WorldUpdate(ByteBuf buf) {
        data = new WorldUpdates(ZLibOperations.decompress(buf));
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.WORLD_UPDATE;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        ZLibOperations.compress(receiver, buf, data);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
