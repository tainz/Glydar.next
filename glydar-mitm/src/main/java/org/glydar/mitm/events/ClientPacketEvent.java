package org.glydar.mitm.events;

import org.glydar.core.protocol.Packet;
import org.glydar.mitm.Relay;

public class ClientPacketEvent extends PacketEvent {

    public ClientPacketEvent(Relay relay, Packet packet) {
        super(relay, packet);
    }
}
