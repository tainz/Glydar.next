package org.glydar.protocol.packet;

import io.netty.buffer.ByteBuf;

import org.glydar.api.entity.Entity;
import org.glydar.protocol.Packet;
import org.glydar.protocol.PacketType;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;

import com.google.common.base.Charsets;

public class Packet10Chat implements Packet {

    private final long   senderId;
    private final String message;

    public Packet10Chat(Entity sender, String message) {
        this.senderId = sender.getId();
        this.message = message;
    }

    public Packet10Chat(ByteBuf buf) {
        this.senderId = -1;

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
    public void writeTo(ByteBuf buf) {
        byte[] msgBuf = message.getBytes(Charsets.UTF_16LE);
        if (senderId == 0) {
            buf.writeLong(senderId);
        }

        buf.writeInt(msgBuf.length / 2);
        buf.writeBytes(msgBuf);
    }

    @Override
    public <T extends Remote> void dispatchTo(ProtocolHandler<T> handler, T remote) {
        handler.handle(remote, this);
    }
}
