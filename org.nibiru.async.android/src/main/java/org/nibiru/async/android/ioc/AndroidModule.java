package org.nibiru.async.android.ioc;


import org.nibiru.async.android.loop.HandlerLooper;
import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.async.core.api.timer.Timer;
import org.nibiru.async.java.timer.JavaTimer;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {
    @Provides
    public Looper getLooper(HandlerLooper looper) {
        return looper;
    }

    @Provides
    public Timer getTimer(JavaTimer timer) {
        return timer;
    }
}
