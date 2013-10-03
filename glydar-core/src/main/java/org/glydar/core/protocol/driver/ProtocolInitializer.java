package org.glydar.core.protocol.driver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;

public class ProtocolInitializer<T extends Remote> extends ChannelInitializer<SocketChannel> {

    private final ProtocolHandler<T> handler;
    private final Object data;

    public ProtocolInitializer(ProtocolHandler<T> handler) {
        this(handler, null);
    }

    public ProtocolInitializer(ProtocolHandler<T> handler, Object data) {
        this.handler = handler;
        this.data = data;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder", new ProtocolDecoder<T>(handler));
        pipeline.addLast("encoder", new ProtocolEncoder<T>(handler));
        pipeline.addLast("dispatcher", new ProtocolDispatcher<T>(handler, data));
    }
}
