package org.nibiru.async.teavm.ioc;

import org.nibiru.async.core.api.loop.Looper;
import org.nibiru.async.core.api.timer.Timer;
import org.nibiru.async.teavm.loop.TeaVmLooper;
import org.nibiru.async.teavm.timer.TeaVmTimer;

import dagger.Module;
import dagger.Provides;

@Module
public class TeaVmModule {
    @Provides
    public Looper getLooper(TeaVmLooper looper) {
        return looper;
    }

    @Provides
    public Timer getTimer(TeaVmTimer timer) {
        return timer;
    }
}
