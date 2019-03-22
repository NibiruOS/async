package org.nibiru.async.swing.ioc;


import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.async.core.api.timer.Timer;
import org.nibiru.async.java.timer.JavaTimer;
import org.nibiru.async.swing.loop.SwingLooper;

import dagger.Module;
import dagger.Provides;

@Module
public class SwingModule {
    @Provides
    public Looper getLooper(SwingLooper looper) {
        return looper;
    }

    @Provides
    public Timer getTimer(JavaTimer timer) {
        return timer;
    }
}
