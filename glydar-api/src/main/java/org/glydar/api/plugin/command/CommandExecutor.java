package org.glydar.api.plugin.command;

import org.glydar.api.plugin.permissions.Permission;

/**
 * Low level interface which defines how a method should be executed.
 */
public interface CommandExecutor {

    String getUsage();

    Permission getPermission();

    int minArgs();

    int maxArgs();

    CommandOutcome execute(CommandSender sender, String[] args);
}
