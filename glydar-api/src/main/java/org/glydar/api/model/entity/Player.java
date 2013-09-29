package org.glydar.api.model.entity;

import org.glydar.api.plugin.command.CommandSender;

public interface Player extends Entity, CommandSender {

    String getIp();

    boolean isAdmin();

    void setAdmin(boolean admin);
}
