package org.glydar.api.plugin.event;


/**
 * Low-level callback called when an Event is fired.
 *
 * @param <E> Type of the expected Event
 */
public interface EventExecutor<E extends Event> {

	void execute(E event);
}
