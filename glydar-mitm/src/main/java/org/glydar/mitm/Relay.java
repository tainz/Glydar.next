package org.glydar.mitm;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.driver.ProtocolInitializer;

public class Relay implements Remote {

    private final Channel clientChannel;
    private final EventLoopGroup serverWorkerGroup;
    private final List<Packet> serverPacketsQueue;
    private Channel serverChannel;

    public Relay(Channel clientChannel) {
        this.clientChannel = clientChannel;
        this.serverWorkerGroup = new NioEventLoopGroup();
        this.serverPacketsQueue = new ArrayList<>();
        this.serverChannel = null;
    }

    public void sendToClient(Packet... packets) {
        for (Packet packet : packets) {
            clientChannel.write(packet);
        }
        clientChannel.flush();
    }

    public void setServerChannel(Channel channel) {
        this.serverChannel = channel;

        for (Packet packet : serverPacketsQueue) {
            serverChannel.write(packet);
        }
        serverChannel.flush();
        serverPacketsQueue.clear();
    }

    public void connectToServer(MitmClient mitmClient) {
        Bootstrap serverRelayBootstrap = new Bootstrap();
        serverRelayBootstrap.group(serverWorkerGroup);
        serverRelayBootstrap.channel(NioSocketChannel.class);
        serverRelayBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        serverRelayBootstrap.handler(new ProtocolInitializer<Relay>(mitmClient, this));
        serverRelayBootstrap.connect("localhost", GlydarMitmMain.getVanillaPort());
    }

    public void sendToServer(Packet... packets) {
        if (serverChannel == null) {
            Collections.addAll(serverPacketsQueue, packets);
        }
        else {
            for (Packet packet : packets) {
                serverChannel.write(packet);
            }
            serverChannel.flush();
        }
    }

    public void closeServerConnection() {
        serverChannel.close();
        serverChannel = null;
    }

    public void shutdownGracefully() {
        closeServerConnection();
        clientChannel.close();
        serverWorkerGroup.shutdownGracefully();
    }
}
