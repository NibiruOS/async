package org.nibiru.async.teavm.timer;

import org.nibiru.async.core.api.timer.Timer;
import org.teavm.jso.browser.Window;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class TeaVmTimer implements Timer {
    @Inject
    public TeaVmTimer() {
    }

    @Override
    public Task schedule(Runnable task, int delayMillis) {
        checkNotNull(task);
        return new TeaVmTask(task, delayMillis, false);
    }

    @Override
    public Task scheduleRepeating(Runnable task, int periodMillis) {
        checkNotNull(task);
        return new TeaVmTask(task, periodMillis, true);
    }

    private static class TeaVmTask implements Task {
        private boolean cancelled;

        private TeaVmTask(Runnable task, int millis, boolean repeat) {
            schedule(task, millis, repeat);
        }

        private void schedule(Runnable task, int millis, boolean repeat) {
            Window.setTimeout(() -> {
                if (!cancelled) {
                    task.run();
                    if (repeat) {
                        schedule(task, millis, repeat);
                    }
                }
            }, millis);
        }

        @Override
        public void cancel() {
            cancelled = true;
        }
    }
}
