package org.eagle.chain.data.rocks.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.eagle.chain.data.config.RocksConfig;
import org.eagle.chain.data.rocks.core.exception.DeleteAllFailedException;
import org.eagle.chain.data.rocks.core.exception.DeleteFailedException;
import org.eagle.chain.data.rocks.core.exception.FindFailedException;
import org.eagle.chain.data.rocks.core.exception.SaveFailedException;
import org.eagle.chain.data.rocks.mapper.Mapper;
import org.eagle.chain.data.rocks.mapper.exception.DeserializationException;
import org.eagle.chain.data.rocks.mapper.exception.SerDeException;
import org.eagle.chain.data.rocks.mapper.exception.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class that should be extended by the concrete asynchronous repository.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public abstract class AsyncKVStore<K, V> implements AsyncKeyValueStore<K, V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncKVStore.class);

    private final KVStore<K, V> underlying;
    private final ExecutorService executorService;

    /**
     * Default constructor which automatically infers key and value types needed for mapper creation.
     *
     * @param configuration for {@link RocksDBConnection}.
     */
    public AsyncKVStore(final RocksDBConfiguration configuration) {
        this.underlying = new KVStore<>(extractKeyType(), extractValueType());
        this.executorService = Executors.newFixedThreadPool(configuration.threadCount());
    }

    /**
     *
     * @param keyType for mapper.
     * @param valueType for mapper.
     */
    public AsyncKVStore(
            final Class<K> keyType,
            final Class<V> valueType
    ) {
        this.underlying = new KVStore<>(keyType, valueType);
        this.executorService = Executors.newFixedThreadPool(RocksConfig.getThreadCount());
    }

    /**
     *
     * @param configuration for {@link RocksDBConnection}.
     * @param keyMapper custom key mapper that implements {@link Mapper}.
     * @param valueMapper custom value mapper that implements {@link Mapper}.
     */
    public AsyncKVStore(
            final RocksDBConfiguration configuration,
            final Mapper<K> keyMapper,
            final Mapper<V> valueMapper
    ) {
        this.underlying = new KVStore<>(keyMapper, valueMapper);
        this.executorService = Executors.newFixedThreadPool(configuration.threadCount());
    }

    @Override
    public CompletableFuture<Void> save(
            final K key,
            final V value
    ) {
        final CompletableFuture<Void> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
                try {
                    underlying.save(key, value);
                    future.complete(null);
                } catch (final SaveFailedException exception) {
                    future.completeExceptionally(exception);
                }
            }, executorService);

        return future;
    }

    @Override
    public CompletableFuture<Optional<V>> findByKey(final K key) {
        final CompletableFuture<Optional<V>> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                future.complete(underlying.findByKey(key));
            } catch (final FindFailedException exception) {
                future.completeExceptionally(exception);
            }
        }, executorService);

        return future;
    }

    @Override
    public CompletableFuture<Collection<V>> findAll() {
        final CompletableFuture<Collection<V>> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                future.complete(underlying.findAll());
            } catch (final DeserializationException exception) {
                future.completeExceptionally(exception);
            }
        }, executorService);

        return future;
    }

    @Override
    public CompletableFuture<Void> deleteByKey(final K key) {
        final CompletableFuture<Void> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                underlying.deleteByKey(key);
                future.complete(null);
            } catch (final DeleteFailedException exception) {
                future.completeExceptionally(exception);
            }
        }, executorService);

        return future;
    }

    @Override
    public CompletableFuture<Void> deleteAll() {
        final CompletableFuture<Void> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            try {
                underlying.deleteAll();
                future.complete(null);
            } catch (final DeleteAllFailedException exception) {
                future.completeExceptionally(exception);
            }
        }, executorService);

        return future;
    }

    @SuppressWarnings("unchecked")
    private Class<K> extractKeyType() {
        return (Class<K>) extractClass(((ParameterizedType) getGenericSuperClass()).getActualTypeArguments()[0]);
    }

    @SuppressWarnings("unchecked")
    private Class<V> extractValueType() {
        return (Class<V>) extractClass(((ParameterizedType) getGenericSuperClass()).getActualTypeArguments()[1]);
    }

    private Type getGenericSuperClass() {
        final Type superClass = getClass().getGenericSuperclass();

        if (superClass instanceof Class<?>) {
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        }

        return superClass;
    }

    private Class<?> extractClass(final Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        }

        throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
    }
}
