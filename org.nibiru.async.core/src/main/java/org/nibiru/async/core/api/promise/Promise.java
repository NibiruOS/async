package org.nibiru.async.core.api.promise;

import com.google.common.base.Function;

import org.nibiru.async.core.api.function.Consumer;

public interface Promise<V, E extends Exception> {
    Promise<V, E> then(Consumer<V> successCallback);

    Promise<V, E> capture(Consumer<E> errorCallback);

    Promise<V, E> last(Runnable finallyCallback);

    <V1> Promise<V1, E> map(Function<V, V1> converter);

    <E1 extends Exception> Promise<V, E1> mapError(Function<E, E1> converter);

    <V1> Promise<V1, E> continueWith(Function<V, Promise<V1, E>> continuation);
}
