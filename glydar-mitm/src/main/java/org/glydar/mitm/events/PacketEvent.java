package org.glydar.mitm.events;

import org.glydar.api.plugin.event.Cancellable;
import org.glydar.api.plugin.event.Event;
import org.glydar.core.protocol.Packet;
import org.glydar.mitm.Relay;

public class PacketEvent extends Event implements Cancellable {

    private final Relay relay;
    private final Packet packet;
    private boolean cancelled;

    public PacketEvent(Relay relay, Packet packet) {
        this.relay = relay;
        this.packet = packet;
    }

    public Relay getRelay() {
        return relay;
    }

    public Packet getPacket() {
        return packet;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
