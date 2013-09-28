package org.glydar.core.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;

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

    public int getVersion() {
        return version;
    }
}
