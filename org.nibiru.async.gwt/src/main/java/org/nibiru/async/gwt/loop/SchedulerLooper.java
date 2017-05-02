package org.nibiru.async.gwt.loop;

import com.google.gwt.core.client.Scheduler;

import org.nibiru.async.core.api.loop.Looper;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class SchedulerLooper implements Looper {
    private final Scheduler scheduler;

    @Inject
    public SchedulerLooper(Scheduler scheduler) {
        this.scheduler = checkNotNull(scheduler);
    }

    @Override
    public void post(final Runnable runnable) {
        checkNotNull(runnable);
        scheduler.scheduleDeferred(() -> {
            runnable.run();
        });
    }
}
