package org.glydar.core.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.core.model.entity.CoreEntity;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.PacketType;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.RemoteType;

import com.google.common.base.Charsets;

public class Packet10Chat implements Packet {

    private final long   senderId;
    private final String message;

    public Packet10Chat(String message) {
        this.senderId = 0l;
        this.message = message;
    }

    public Packet10Chat(CoreEntity sender, String message) {
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

    public long getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }
}
