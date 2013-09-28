package org.glydar.api.plugin.event;

import org.glydar.api.plugin.Plugin;

public interface EventManager {

    /**
     * Used to register all event-methods in a specific Listener class.
     * 
     * @param plugin
     *            The main {@link Plugin} instance used by the plugin.
     * @param listener
     *            The class implementing listener that contains all the
     *            event-methods.
     */
    boolean register(Plugin plugin, Listener listener);

    <E extends Event> void register(Plugin plugin, Class<E> eventClass, EventExecutor<E> executor,
            EventPriority priority);

    void unregister(EventExecutor<?> executor);

    void unregister(Listener listener);

    void unregisterAll(Plugin plugin);

    <E extends Event> E callEvent(E event);

}
