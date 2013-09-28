package org.glydar.api.plugin.scheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.glydar.api.plugin.Plugin;

/**
 * GlydarFuture copied from CraftFuture by the Bukkit project.
 * 
 * @author YoshiGenius
 */
class GlydarFuture<T> extends GlydarTask implements Future<T> {

    private final Callable<T> callable;
    private T                 value;
    private Exception         exception = null;

    GlydarFuture(final Callable<T> callable, final Plugin plugin, final int id) {
        super(plugin, null, id, -1l);
        this.callable = callable;
    }

    @Override
    public synchronized boolean cancel(final boolean mayInterruptIfRunning) {
        if (getInterval() != -1l) {
            return false;
        }
        setInterval(-2l);
        return true;
    }

    @Override
    public boolean isCancelled() {
        return getInterval() == -2l;
    }

    @Override
    public boolean isDone() {
        final long period = this.getInterval();
        return period != -1l && period != -3l;
    }

    @Override
    public T get() throws CancellationException, InterruptedException, ExecutionException {
        try {
            return get(0, TimeUnit.MILLISECONDS);
        }
        catch (final TimeoutException e) {
            throw new Error(e);
        }
    }

    @Override
    public synchronized T get(long timeout, final TimeUnit unit) throws InterruptedException, ExecutionException,
            TimeoutException {
        timeout = unit.toMillis(timeout);
        long period = this.getInterval();
        long timestamp = timeout > 0 ? System.currentTimeMillis() : 0l;
        while (true) {
            if (period == -1l || period == -3l) {
                this.wait(timeout);
                period = this.getInterval();
                if (period == -1l || period == -3l) {
                    if (timeout == 0l) {
                        continue;
                    }
                    timeout += timestamp - (timestamp = System.currentTimeMillis());
                    if (timeout > 0) {
                        continue;
                    }
                    throw new TimeoutException();
                }
            }
            if (period == -2l) {
                throw new CancellationException();
            }
            if (period == -4l) {
                if (exception == null) {
                    return value;
                }
                throw new ExecutionException(exception);
            }
            throw new IllegalStateException("Expected " + -1l + " to " + -4l + ", got " + period);
        }
    }

    @Override
    public void run() {
        synchronized (this) {
            if (getInterval() == -2l) {
                return;
            }
            setInterval(-3l);
        }
        try {
            value = callable.call();
        }
        catch (final Exception e) {
            exception = e;
        }
        finally {
            synchronized (this) {
                setInterval(-4l);
                this.notifyAll();
            }
        }
    }

    @Override
    synchronized boolean cancel0() {
        if (getInterval() != -1l) {
            return false;
        }
        setInterval(-2l);
        notifyAll();
        return true;
    }
}
