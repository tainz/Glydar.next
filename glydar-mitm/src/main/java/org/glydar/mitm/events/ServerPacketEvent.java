package org.glydar.mitm.events;

import org.glydar.core.protocol.Packet;
import org.glydar.mitm.Relay;

public class ServerPacketEvent extends PacketEvent {

    public ServerPacketEvent(Relay relay, Packet packet) {
        super(relay, packet);
    }
}
