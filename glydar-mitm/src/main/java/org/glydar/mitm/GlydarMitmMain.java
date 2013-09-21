package org.glydar.mitm;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.logging.Level;

import org.glydar.api.Glydar;
import org.glydar.protocol.driver.ProtocolInitializer;

public class GlydarMitmMain {

    private static final int         SERVER_PORT = 12346;

    private static MitmServer        mitm;
    private static NioEventLoopGroup bossGroup;
    private static NioEventLoopGroup workerGroup;

    public static void main(String[] args) {
        Glydar.getLogger().getJdkLogger().setLevel(Level.ALL);
        Glydar.getLogger().info("Starting {0} version {1}", Glydar.getName(), Glydar.getVersion());

        mitm = new MitmServer();
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        ServerBootstrap mitmBootstrap = new ServerBootstrap();
        mitmBootstrap.group(bossGroup, workerGroup);
        mitmBootstrap.channel(NioServerSocketChannel.class);
        mitmBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        mitmBootstrap.childHandler(new ProtocolInitializer<Relay>(mitm));
        mitmBootstrap.bind(SERVER_PORT);

        Glydar.getLogger().info("Started on port {0}", SERVER_PORT);
    }

    public static void shutdown() {
        mitm.shutdownGracefully();
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
