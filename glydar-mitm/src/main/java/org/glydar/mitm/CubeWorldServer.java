package org.glydar.mitm;

import io.netty.channel.ChannelHandlerContext;

import org.glydar.protocol.Packet;
import org.glydar.protocol.Remote;

public class CubeWorldServer implements Remote {

    private final ChannelHandlerContext context;

    public CubeWorldServer(ChannelHandlerContext context) {
        this.context = context;
    }

    public void send(Packet... packets) {
        if (packets.length < 0) {
            return;
        }

        for (Packet packet : packets) {
            context.writeAndFlush(packet);
        }
    }

    public void closeConnection() {
        context.close();
    }
}
