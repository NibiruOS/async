package org.nibiru.async.gwt.ioc;


import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.async.core.api.timer.Timer;
import org.nibiru.async.gwt.loop.SchedulerLooper;
import org.nibiru.async.gwt.timer.GwtTimer;

import dagger.Module;
import dagger.Provides;

@Module
public class GwtModule {
    @Provides
    public Looper getLooper(SchedulerLooper looper) {
        return looper;
    }


    @Provides
    public Timer getTimer(GwtTimer timer) {
        return timer;
    }
}
