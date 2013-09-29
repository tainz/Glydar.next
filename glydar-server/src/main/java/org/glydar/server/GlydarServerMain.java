package org.glydar.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.TimeUnit;

import org.glydar.api.Glydar;
import org.glydar.core.protocol.driver.ProtocolInitializer;

import com.google.common.base.Stopwatch;

public class GlydarServerMain {

    public static void main(String[] args) {
        Stopwatch watch = Stopwatch.createStarted();

        GlydarServer server = (GlydarServer) Glydar.getBackend();

        server.getLogger().info("Starting server {0} version {1}", Glydar.getName(), Glydar.getVersion());

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new ProtocolInitializer<>(server));
        bootstrap.bind(server.getConfig().getPort());

        server.getLogger().info("Server ready on port {0}", server.getConfig().getPort());
        server.getLogger().info("This server is running {0} version {1}", server.getName(), server.getVersion());

        watch.stop();
        server.getLogger().info("Server started in {0}ms", watch.elapsed(TimeUnit.MILLISECONDS));

        while (true) {
            try {
                Thread.sleep(1);
            }
            catch (InterruptedException e) {
            }
        }
    }
}
