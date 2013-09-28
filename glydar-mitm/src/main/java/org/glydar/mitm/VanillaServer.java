package org.glydar.mitm;

import io.netty.channel.Channel;

import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.Remote;

public class VanillaServer implements Remote {

    private final Channel channel;

    public VanillaServer(Channel channel) {
        this.channel = channel;
    }

    public void send(Packet... packets) {
        for (Packet packet : packets) {
            channel.write(packet);
        }
        channel.flush();
    }

    public void closeConnection() {
        channel.close();
    }
}
