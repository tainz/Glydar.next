package org.glydar.protocol.driver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;

public class ProtocolInitializer<T extends Remote> extends ChannelInitializer<SocketChannel> {

    private final ProtocolHandler<T> handler;

    public ProtocolInitializer(ProtocolHandler<T> handler) {
        this.handler = handler;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder", new ProtocolDecoder<T>(handler));
        pipeline.addLast("encoder", new ProtocolEncoder<T>(handler));
        pipeline.addLast("dispatcher", new ProtocolDispatcher<T>(handler));
    }
}
