package org.nibiru.async.core.api.timer;


/**
 * A timer task factory.
 */
public interface Timer {
    Task schedule(Runnable task, int delayMillis);

    Task scheduleRepeating(Runnable task, int periodMillis);

    interface Task {
        void cancel();
    }
}
