package org.glydar.core.model.entity;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

import org.glydar.api.Glydar;
import org.glydar.api.model.entity.Player;
import org.glydar.api.plugin.permissions.Permission;
import org.glydar.api.plugin.permissions.Permission.PermissionDefault;
import org.glydar.api.plugin.permissions.PermissionAttachment;
import org.glydar.core.protocol.Packet;
import org.glydar.core.protocol.Remote;

public class CorePlayer extends CoreEntity implements Player, Remote {

    private final Channel channel;
    private boolean       admin;
    private boolean		  connected = false;

    public CorePlayer(Channel channel) {
    	super();
        this.channel = channel;
    }

    @Override
    public String getIp() {
        SocketAddress address = channel.remoteAddress();
        if (address instanceof InetSocketAddress) {
            return ((InetSocketAddress) address).getAddress().getHostAddress();
        }
        else {
            return "";
        }
    }

    @Override
    public String getName() {
        return data.getName();
    }

    @Override
    public void sendMessage(String message) {
    }

    @Override
    public boolean isAdmin() {
        return admin;
    }

    @Override
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean hasPermission(String permission) {
        return hasPermission(new Permission(permission, PermissionDefault.FALSE));
    }

    @Override
    public boolean hasPermission(Permission permission) {
        if (getAttachments() == null || getAttachments().isEmpty()) {
            switch (permission.getPermissionDefault()) {
            case TRUE:
                return true;
            case FALSE:
                return false;
            case ADMIN:
                return isAdmin();
            case NON_ADMIN:
                return (!isAdmin());
            }
        }

        for (PermissionAttachment attachment : getAttachments()) {
            if (attachment.hasPermission(permission)) {
                return true;
            }
        }

        return false;
    }

    public List<PermissionAttachment> getAttachments() {
        return PermissionAttachment.getAttachments(this);
    }

    public void addAttachment(PermissionAttachment attachment) {
        PermissionAttachment.addAttachment(attachment);
    }

    public void sendPackets(Packet... packets) {
        for (Packet packet : packets) {
            channel.write(packet);
        }

        channel.flush();
    }
    
    public boolean isConnected () {
    	return connected;
    }
    
    public void setConnected() {
    	connected = true;
    }

    @Override
    public void remove(){
    	super.remove();
    	connected = false;
    	channel.close();
    }
    
}
