package org.glydar.mitm;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.List;

import org.glydar.core.model.entity.CoreEntityData;
import org.glydar.core.model.entity.EntityChanges;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.driver.ProtocolInitializer;
import org.glydar.core.protocol.packet.Packet00EntityUpdate;
import org.glydar.core.protocol.packet.Packet17VersionExchange;

public class Relay implements Remote {

    private final Channel clientChannel;
    private final EventLoopGroup serverWorkerGroup;
    private final List<Packet> serverPacketsQueue;
    private Channel serverChannel;

    private long entityId;
    private final CoreEntityData entityData;

    public Relay(Channel clientChannel) {
        this.clientChannel = clientChannel;
        this.serverWorkerGroup = new NioEventLoopGroup();
        this.serverPacketsQueue = new ArrayList<>();
        this.serverChannel = null;
        this.entityId = -1;
        this.entityData = new CoreEntityData(new EntityChanges());
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

    public void connectToServer() {
        GlydarMitm mitm = GlydarMitm.getInstance();

        Bootstrap serverRelayBootstrap = new Bootstrap();
        serverRelayBootstrap.group(serverWorkerGroup);
        serverRelayBootstrap.channel(NioSocketChannel.class);
        serverRelayBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        serverRelayBootstrap.handler(new ProtocolInitializer<Relay>(GlydarMitm.getInstance().getMitmClient(), this));
        serverRelayBootstrap.connect(mitm.getConfig().getVanillaHost(), mitm.getConfig().getVanillaPort());
    }

    public void sendToServer(Packet... packets) {
        if (serverChannel == null) {
            for (Packet packet : packets) {
                // Packet 0 will be sent in one row when the server is up
                if (!(packet instanceof Packet00EntityUpdate)) {
                    serverPacketsQueue.add(packet);
                }
            }
        }
        else {
            for (Packet packet : packets) {
                serverChannel.write(packet);
            }
            serverChannel.flush();
        }
    }

    public void closeServerConnection() {
        if (serverChannel == null) {
            return;
        }

        serverChannel.close();
        serverChannel = null;
    }

    public void shutdownGracefully() {
        closeServerConnection();
        clientChannel.close();
        serverWorkerGroup.shutdownGracefully();
    }

    public boolean hasJoined() {
        return entityId >= 0;
    }

    public long getEntityId() {
        if (entityId < 0) {
            throw new IllegalStateException("Player has not joined yet");
        }

        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public CoreEntityData getEntityData() {
        return entityData;
    }

    public void prepareReconnection() {
        closeServerConnection();
        serverPacketsQueue.add(new Packet17VersionExchange(ProtocolHandler.VERSION));
        serverPacketsQueue.add(new Packet00EntityUpdate(entityId, entityData));
    }
}
