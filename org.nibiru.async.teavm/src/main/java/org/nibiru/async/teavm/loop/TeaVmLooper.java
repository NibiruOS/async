package org.nibiru.async.teavm.loop;

import org.nibiru.async.core.api.loop.Looper;
import org.teavm.jso.browser.Window;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class TeaVmLooper implements Looper {
    @Inject
    public TeaVmLooper() {
    }

    @Override
    public void post(final Runnable runnable) {
        checkNotNull(runnable);
        Window.setTimeout(() -> {
            runnable.run();
        }, 0);
    }
}
