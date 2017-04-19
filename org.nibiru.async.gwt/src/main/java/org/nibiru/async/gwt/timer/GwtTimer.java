package org.nibiru.async.gwt.timer;

import org.nibiru.async.core.api.timer.Timer;

import static com.google.common.base.Preconditions.checkNotNull;

public class GwtTimer implements Timer {
    @Override
    public Task schedule(Runnable task, int delayMillis) {
        checkNotNull(task);
        com.google.gwt.user.client.Timer timer = gwtTimer(task);
        timer.schedule(delayMillis);
        return task(timer);
    }

    @Override
    public Task scheduleRepeating(Runnable task, int periodMillis) {
        checkNotNull(task);
        com.google.gwt.user.client.Timer timer = gwtTimer(task);
        timer.scheduleRepeating(periodMillis);
        return task(timer);
    }

    private com.google.gwt.user.client.Timer gwtTimer(Runnable task) {
        return new com.google.gwt.user.client.Timer() {
            @Override
            public void run() {
                task.run();
            }
        };
    }

    private Task task(com.google.gwt.user.client.Timer task) {
        return () -> task.cancel();
    }
}
