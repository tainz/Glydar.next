package org.glydar.protocol.exceptions;

import org.glydar.protocol.PacketType;

public class ServerOnlyPacketException extends RuntimeException {

    private static final long serialVersionUID = 6654591783618203766L;

    public ServerOnlyPacketException(PacketType type) {
        super("Packet " + type + " is server only");
    }
}
