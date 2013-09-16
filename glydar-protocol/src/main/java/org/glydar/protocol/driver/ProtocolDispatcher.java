package org.glydar.protocol.driver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.io.IOException;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.protocol.Packet;
import org.glydar.protocol.ProtocolHandler;
import org.glydar.protocol.Remote;
import org.glydar.protocol.exceptions.ProtocolHandlerException;

public class ProtocolDispatcher<T extends Remote> extends SimpleChannelInboundHandler<Packet> {

    private final GlydarLogger       logger;
    private final AttributeKey<T>    remoteKey;
    private final ProtocolHandler<T> handler;

    public ProtocolDispatcher(GlydarLogger logger, ProtocolHandler<T> handler) {
        this.logger = logger;
        this.remoteKey = new AttributeKey<T>(handler.getClass().getSimpleName());
        this.handler = handler;
    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        Attribute<T> clientAttribute = context.attr(remoteKey);
        T client = clientAttribute.get();
        if (client != null) {
            throw new RuntimeException("Tried to create a remote when one already existed");
        }

        client = handler.createRemote(context);
        clientAttribute.set(client);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Packet packet) throws Exception {
        try {
            channelRead1(context, packet);
        }
        catch (IOException exc) {
            throw new IOException(exc);
        }
        catch (Exception exc) {
            throw new ProtocolHandlerException(exc, packet);
        }
    }

    private void channelRead1(ChannelHandlerContext context, Packet packet) throws Exception {
        T remote = context.attr(remoteKey).get();
        packet.dispatchTo(handler, remote);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        // In case things go reaallly wrong.
        try {
            exceptionCaught0(context, cause);
        }
        catch (Exception exc) {
            logger.severe(exc, "Error while handling error in {0}", getClass().getCanonicalName());
        }
    }

    private void exceptionCaught0(ChannelHandlerContext context, Throwable cause) {
        T remote = context.attr(remoteKey).get();

        if (cause instanceof IOException) {
            if (remote != null) {
                logger.info("{0} lost connection !", context.channel().remoteAddress());
                handler.disconnect(remote);
            }
        }
        else if (cause instanceof ProtocolHandlerException) {
            Packet packet = ((ProtocolHandlerException) cause).getPacket();
            logger.warning(cause.getCause(), "Error while handling packet {0} for {1}", packet.getPacketType(), context
                    .channel().remoteAddress());
        }
        else {
            logger.severe(cause, "Unexpected error while handling packet in {0}", getClass().getCanonicalName());
        }
    }
}
