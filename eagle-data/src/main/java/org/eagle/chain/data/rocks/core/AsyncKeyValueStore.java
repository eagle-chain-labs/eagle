package org.eagle.chain.data.rocks.core;

import org.eagle.chain.data.rocks.core.exception.DeleteAllFailedException;
import org.eagle.chain.data.rocks.core.exception.DeleteFailedException;
import org.eagle.chain.data.rocks.core.exception.FindFailedException;
import org.eagle.chain.data.rocks.core.exception.SaveFailedException;
import org.eagle.chain.data.rocks.mapper.exception.DeserializationException;
import org.eagle.chain.data.rocks.mapper.exception.SerDeException;
import org.eagle.chain.data.rocks.mapper.exception.SerializationException;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletionStage;


/**
 *  Interface that defines asynchronous operations against Key-Value Store.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public interface AsyncKeyValueStore<K, V> {

    /**
     * Inserts key-value pair into RocksDB asynchronously.
     *
     * @param key of value.
     * @param value that should be persisted.
     * @return CompletableFuture of Void.
     * @throws SerializationException when it's not possible to serialize entity.
     * @throws SaveFailedException when it's not possible to persist entity.
     */
    CompletionStage<Void> save(K key, V value) throws SerializationException, SaveFailedException;

    /**
     * Try to find value for a given key asynchronously.
     *
     * @param key of entity that should be retrieved.
     * @return CompletableFuture of Optional of entity.
     * @throws SerDeException when it's not possible to serialize/deserialize entity.
     * @throws FindFailedException when it's not possible to retrieve a wanted entity.
     */
    CompletionStage<Optional<V>> findByKey(K key) throws SerDeException, FindFailedException;

    /**
     * Try to find all entities from repository asynchronously.
     *
     * @return CompletableFuture of Collection of entities.
     * @throws DeserializationException when it's not possible to deserialize entity.
     */
    CompletionStage<Collection<V>> findAll() throws DeserializationException;

    /**
     * Delete entity for a given key asynchronously..
     *
     * @param key of entity that should be deleted.
     * @return CompletableFuture of Void.
     * @throws SerializationException when it's not possible to serialize entity.
     * @throws DeleteFailedException when it's not possible to delete a wanted entity.
     */
    CompletionStage<Void> deleteByKey(K key) throws SerializationException, DeleteFailedException;

    /**
     * Deletes all entities from RocksDB asynchronously.
     *
     * @return CompletableFuture of Void.
     * @throws DeleteAllFailedException when it's not possible to delete entity.
     */
    CompletionStage<Void> deleteAll() throws DeleteAllFailedException;
}
