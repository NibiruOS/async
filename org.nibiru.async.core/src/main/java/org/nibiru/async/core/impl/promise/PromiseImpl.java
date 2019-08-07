package org.nibiru.async.core.impl.promise;

import com.google.common.base.Function;

import org.nibiru.async.core.api.function.Consumer;
import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;

import static com.google.common.base.Preconditions.checkNotNull;

public class PromiseImpl<V, E extends Exception> extends BasePromise<V, E> {
    private boolean resolved;
    private V value;
    private boolean rejected;
    private E exception;
    private Consumer<V> successCallback;
    private Consumer<E> errorCallback;
    private Runnable finallyCallback;

    public void resolve(V value) {
        resolved = true;
        this.value = value;
        if (successCallback != null) {
            try {
                successCallback.accept(value);
            } catch (Exception e) {
                errorCallback.accept((E) e);
            }
        }
        if (finallyCallback != null) {
            try {
                finallyCallback.run();
            } catch (Exception e) {
                errorCallback.accept((E) e);
            }
        }
    }

    public void reject(E exception) {
        rejected = true;
        this.exception = exception;
        if (errorCallback != null) {
            errorCallback.accept(exception);
        }
        if (finallyCallback != null) {
            try {
                finallyCallback.run();
            } catch (Exception e) {
                errorCallback.accept((E) e);
            }
        }
    }

    @Override
    public Promise<V, E> then(Consumer<V> successCallback) {
        this.successCallback = checkNotNull(successCallback);
        if (resolved) {
            try {
                successCallback.accept(value);
            } catch (Exception e) {
                errorCallback.accept((E) e);
            }
        }
        return this;
    }

    @Override
    public Promise<V, E> capture(Consumer<E> errorCallback) {
        this.errorCallback = checkNotNull(errorCallback);
        if (rejected) {
            errorCallback.accept(exception);
        }
        return this;
    }

    @Override
    public Promise<V, E> last(Runnable finallyCallback) {
        this.finallyCallback = checkNotNull(finallyCallback);
        if (resolved || rejected) {
            try {
                finallyCallback.run();
            } catch (Exception e) {
                errorCallback.accept((E) e);
            }
        }
        return this;
    }
}
