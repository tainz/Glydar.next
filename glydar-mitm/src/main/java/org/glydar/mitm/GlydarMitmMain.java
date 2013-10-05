package org.glydar.mitm;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.glydar.api.Glydar;
import org.glydar.api.logging.GlydarLogger;
import org.glydar.core.protocol.driver.ProtocolInitializer;

public class GlydarMitmMain {

    private static NioEventLoopGroup bossGroup;
    private static NioEventLoopGroup workerGroup;

    public static void main(String[] args) {
        GlydarLogger logger = Glydar.getLogger(GlydarMitmMain.class, "Boot");
        logger.info("Starting {0} version {1}", Glydar.getName(), Glydar.getVersion());

        GlydarMitm mitm = (GlydarMitm) Glydar.getBackend();

        mitm.getVanillaServer().start();

        int mitmPort = mitm.getConfig().getMitmPort();
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        ServerBootstrap mitmBootstrap = new ServerBootstrap();
        mitmBootstrap.group(bossGroup, workerGroup);
        mitmBootstrap.channel(NioServerSocketChannel.class);
        mitmBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        mitmBootstrap.childHandler(new ProtocolInitializer<>(mitm.getMitmServer()));
        mitmBootstrap.bind(mitmPort);

        logger.info("Started on port {0}", mitmPort);
        logger.info("Relaying to port {0} {1}", mitm.getConfig().getVanillaHost(), mitm.getConfig().getVanillaPort());
    }

    public static void shutdown() {
        MitmServer mitmServer = GlydarMitm.getInstance().getMitmServer();

        mitmServer.shutdownGracefully();
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
