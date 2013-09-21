package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.api.entity.Entity;
import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.RemoteType;

import com.google.common.base.Charsets;

public class Packet10Chat implements Packet {

    private final long   senderId;
    private final String message;

    public Packet10Chat(Entity sender, String message) {
        this.senderId = sender.getId();
        this.message = message;
    }

    public Packet10Chat(RemoteType sender, ByteBuf buf) {
        if (sender == RemoteType.CLIENT) {
            this.senderId = -1l;
        }
        else {
            this.senderId = buf.readLong();
        }

        int length = buf.readInt();
        byte[] messageBytes = new byte[length * 2];
        buf.readBytes(messageBytes);
        this.message = new String(messageBytes, Charsets.UTF_16LE);
    }

    @Override
    public PacketType getPacketType() {
        return PacketType.CHAT;
    }

    @Override
    public void writeTo(RemoteType receiver, ByteBuf buf) {
        if (receiver == RemoteType.CLIENT) {
            buf.writeLong(senderId);
        }

        byte[] messageBytes = message.getBytes(Charsets.UTF_16LE);
        buf.writeInt(messageBytes.length / 2);
        buf.writeBytes(messageBytes);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
