package org.glydar.api.plugin.command;

import org.glydar.api.plugin.Plugin;

public interface CommandManager {

    /**
     * Selectively registers a list of commands by name.
     * 
     * @param plugin
     *            The main {@link Plugin} class of the plugin that is
     *            registering this command.
     * @param set
     *            The class implementing {@link CommandSet} containing the
     *            commands.
     * @param name
     *            A set of strings representing the names of the commands being
     *            registered.
     */
    void register(Plugin plugin, CommandSet set, String... name);

    /**
     * Registers all commands in a class that implements CommandSet
     * 
     * @param plugin
     *            The main {@link Plugin} class of the plugin that is
     *            registering this command.
     * @param set
     *            The class implementing {@link CommandSet} containing the
     *            commands.
     */
    void register(Plugin plugin, CommandSet set);

    void register(Plugin plugin, CommandName name, CommandExecutor executor, String... aliases);

    CommandOutcome execute(CommandSender sender, String commandLine);

    CommandOutcome execute(CommandSender sender, String... args);

    CommandOutcome execute(CommandSender sender, CommandName name, String... args);
}
