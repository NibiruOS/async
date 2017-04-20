package org.nibiru.async.java.timer;

import org.nibiru.async.core.api.timer.Timer;

import java.util.TimerTask;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class JavaTimer implements Timer {
    private java.util.Timer timer = new java.util.Timer();

    @Inject
    public JavaTimer(){
    }

    @Override
    public Task schedule(Runnable task, int delayMillis) {
        checkNotNull(task);
        TimerTask timerTask = timerTask(task);
        timer.schedule(timerTask, delayMillis);
        return task(timerTask);
    }

    @Override
    public Task scheduleRepeating(Runnable task, int periodMillis) {
        checkNotNull(task);
        TimerTask timerTask = timerTask(task);
        timer.schedule(timerTask, 0, periodMillis);
        return task(timerTask);
    }

    private TimerTask timerTask(Runnable task){
        return new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        };
    }

    private Task task(TimerTask task){
        return () -> task.cancel();
    }
}
