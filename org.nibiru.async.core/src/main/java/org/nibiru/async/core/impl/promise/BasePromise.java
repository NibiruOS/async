package org.nibiru.async.core.impl.promise;


import com.google.common.base.Function;
import com.google.common.base.Functions;

import org.nibiru.async.core.api.promise.Deferred;
import org.nibiru.async.core.api.promise.Promise;

abstract class BasePromise<V, E extends Exception> implements Promise<V, E> {
    @Override
    public <V1> Promise<V1, E> map(Function<V, V1> converter) {
        return new MappedPromise<>(this, converter);
    }

    @Override
    public <E1 extends Exception> Promise<V, E1> mapError(Function<E, E1> converter) {
        return new MappedErrorPromise<>(this, converter);
    }

    @Override
    public <V1> Promise<V1, E> continueWith(Function<V, Promise<V1, E>> continuation) {
        return continueWith(continuation, Functions.identity());
    }

    @Override
    public <V1, E1 extends Exception> Promise<V1, E1> continueWith(Function<V, Promise<V1, E1>> continuation,
                                                                   Function<E, E1> exceptionConverter) {
        Deferred<V1, E1> deferred = Deferred.defer();
        then(result -> {
            continuation.apply(result)
                    .then(continuationResult -> deferred.resolve(continuationResult))
                    .capture(continuationError -> deferred.reject(continuationError));
        });
        capture(continuationError -> deferred.reject(exceptionConverter.apply(continuationError)));
        return deferred.promise();
    }
}
