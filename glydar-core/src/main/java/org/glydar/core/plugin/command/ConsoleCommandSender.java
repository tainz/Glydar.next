package org.glydar.core.plugin.command;

import org.glydar.api.logging.GlydarLogger;
import org.glydar.api.plugin.command.CommandSender;
import org.glydar.api.plugin.permissions.Permission;

/**
 * Represents a {@link CommandSender} for the console input.
 */
public class ConsoleCommandSender implements CommandSender {

    private final GlydarLogger logger;

    public ConsoleCommandSender(GlydarLogger logger) {
        this.logger = logger;
    }

    @Override
    public String getName() {
        return "*Console*";
    }

    @Override
    public void sendMessage(String message) {
        logger.info(message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return true;
    }
}
