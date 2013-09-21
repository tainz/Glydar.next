package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.RemoteType;

public class Packet17VersionExchange implements Packet {

    private final int version;

    public Packet17VersionExchange(int serverVersion) {
        this.version = serverVersion;
    }

    public Packet17VersionExchange(ByteBuf buf) {
        version = buf.readInt();
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.VERSION_EXCHANGE;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        buf.writeInt(version);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
