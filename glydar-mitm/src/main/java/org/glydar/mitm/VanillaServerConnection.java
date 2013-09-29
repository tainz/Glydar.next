package org.glydar.mitm;

import io.netty.channel.Channel;

import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.Remote;

public class VanillaServerConnection implements Remote {

    private final Channel channel;

    public VanillaServerConnection(Channel channel) {
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
