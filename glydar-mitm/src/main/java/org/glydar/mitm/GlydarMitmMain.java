package org.glydar.mitm;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.logging.Level;

import org.glydar.api.Glydar;
import org.glydar.protocol.driver.ProtocolInitializer;

public class GlydarMitmMain {

    private static final int         DEFAULT_MITM_PORT   = 12346;
    private static final int         DEFAULT_SERVER_PORT = 12345;

    private static int               serverPort;
    private static MitmServer        mitm;
    private static NioEventLoopGroup bossGroup;
    private static NioEventLoopGroup workerGroup;

    public static void main(String[] args) {
        Glydar.getLogger().getJdkLogger().setLevel(Level.INFO);
        Glydar.getLogger().info("Starting {0} version {1}", Glydar.getName(), Glydar.getVersion());

        int mitmPort;
        switch (args.length) {
        case 0:
            mitmPort = DEFAULT_MITM_PORT;
            serverPort = DEFAULT_SERVER_PORT;
            break;
        case 2:
            try {
                mitmPort = Integer.parseInt(args[0]);
                serverPort = Integer.parseInt(args[1]);
                break;
            }
            catch (NumberFormatException exc) {
            }
        default:
            Glydar.getLogger().info("Invalid arguments");
            return;
        }

        mitm = new MitmServer();
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        ServerBootstrap mitmBootstrap = new ServerBootstrap();
        mitmBootstrap.group(bossGroup, workerGroup);
        mitmBootstrap.channel(NioServerSocketChannel.class);
        mitmBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        mitmBootstrap.childHandler(new ProtocolInitializer<Relay>(mitm));
        mitmBootstrap.bind(mitmPort);

        Glydar.getLogger().info("Started on port {0}", mitmPort);
        Glydar.getLogger().info("Relaying to port {0}", serverPort);
    }

    public static void shutdown() {
        mitm.shutdownGracefully();
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static int getServerPort() {
        return serverPort;
    }
}
