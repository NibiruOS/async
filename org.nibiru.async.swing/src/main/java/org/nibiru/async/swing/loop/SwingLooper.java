package org.nibiru.async.swing.loop;

import org.nibiru.async.core.api.loop.Looper;

import javax.inject.Inject;
import javax.swing.SwingUtilities;

import static com.google.common.base.Preconditions.checkNotNull;

public class SwingLooper implements Looper {
	@Inject
	public SwingLooper() {
	}

	@Override
	public void post(Runnable runnable) {
		checkNotNull(runnable);
		SwingUtilities.invokeLater(runnable);
	}
}
