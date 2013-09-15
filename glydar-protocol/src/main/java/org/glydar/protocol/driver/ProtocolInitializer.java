package org.glydar.protocol.driver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;

public class ProtocolInitializer<T extends Remote> extends ChannelInitializer<SocketChannel> {

    private final GlydarLogger       logger;
    private final ProtocolHandler<T> handler;

    public ProtocolInitializer(GlydarLogger logger, ProtocolHandler<T> handler) {
        this.logger = logger;
        this.handler = handler;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("decoder", new ProtocolDecoder(logger));
        pipeline.addLast("encoder", new ProtocolEncoder(logger));
        pipeline.addLast("dispatcher", new ProtocolDispatcher<T>(logger, handler));
    }
}
