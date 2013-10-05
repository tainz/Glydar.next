package org.glydar.core.protocol.driver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.IOException;

import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.ProtocolHandler;
import org.glydar.core.protocol.Remote;
import org.glydar.core.protocol.exceptions.ProtocolHandlerException;

public class ProtocolDispatcher<T extends Remote> extends SimpleChannelInboundHandler<Packet> {

    private final ProtocolHandler<T> handler;
    private final Object data;
    private T                        remote;

    public ProtocolDispatcher(ProtocolHandler<T> handler, Object data) {
        this.handler = handler;
        this.data = data;
        this.remote = null;
    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        if (remote != null) {
            throw new RuntimeException("Tried to create a remote when one already existed");
        }

        handler.getLogger().info("{0} connected", context.channel().remoteAddress());
        this.remote = handler.createRemote(context.channel(), data);
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) {
        if (remote == null) {
            throw new RuntimeException("Tried to disconnect remote before it has been created");
        }

        handler.getLogger().info("{0} disconnected", context.channel().remoteAddress());
        handler.disconnect(remote);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Packet packet) throws Exception {
        try {
            packet.dispatchTo(handler, remote);
        }
        catch (Exception exc) {
            throw new ProtocolHandlerException(exc, packet);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        // In case things go reaallly wrong.
        try {
            exceptionCaught0(context, cause);
        }
        catch (Exception exc) {
            handler.getLogger().severe(exc, "Error while handling error in {0}", getClass().getCanonicalName());
        }
    }

    private void exceptionCaught0(ChannelHandlerContext context, Throwable cause) {
        if (cause instanceof IOException) {
            if (remote != null) {
                handler.getLogger().info("{0} lost connection !", context.channel().remoteAddress());
            }
        }
        else if (cause instanceof ProtocolHandlerException) {
            Packet packet = ((ProtocolHandlerException) cause).getPacket();
            handler.getLogger().warning(cause.getCause(), "Error while handling packet {0} for {1}",
                    packet.getPacketType(), context.channel().remoteAddress());
        }
        else {
            handler.getLogger().severe(cause, "Unexpected error while handling packet in {0}",
                    getClass().getCanonicalName());
        }
    }
}
