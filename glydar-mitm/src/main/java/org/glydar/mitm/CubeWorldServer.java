package org.glydar.mitm;

import io.netty.channel.Channel;

import org.glydar.protocol.Packet;
import org.glydar.protocol.Remote;

public class CubeWorldServer implements Remote {

    private final Channel channel;

    public CubeWorldServer(Channel channel) {
        this.channel = channel;
    }

    public void send(Packet... packets) {
        if (packets.length < 0) {
            return;
        }

        for (Packet packet : packets) {
            channel.writeAndFlush(packet);
        }
    }

    public void closeConnection() {
        channel.close();
    }
}
