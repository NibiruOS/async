package org.nibiru.async.ios.ioc;


import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.async.core.api.timer.Timer;
import org.nibiru.async.ios.loop.NSThreadLooper;
import org.nibiru.async.java.timer.JavaTimer;

import dagger.Module;
import dagger.Provides;

@Module
public class IOSModule {
    @Provides
    public Looper getLooper(NSThreadLooper looper) {
        return looper;
    }

    @Provides
    public Timer getTimer(JavaTimer timer) {
        return timer;
    }
}
