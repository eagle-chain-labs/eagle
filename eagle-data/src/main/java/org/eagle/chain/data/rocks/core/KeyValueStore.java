package org.eagle.chain.data.rocks.core;

import java.util.Collection;
import java.util.Optional;
import org.eagle.chain.data.rocks.core.exception.DeleteAllFailedException;
import org.eagle.chain.data.rocks.core.exception.DeleteFailedException;
import org.eagle.chain.data.rocks.core.exception.FindFailedException;
import org.eagle.chain.data.rocks.core.exception.SaveFailedException;
import org.eagle.chain.data.rocks.mapper.exception.DeserializationException;
import org.eagle.chain.data.rocks.mapper.exception.SerDeException;
import org.eagle.chain.data.rocks.mapper.exception.SerializationException;

/**
 *  Interface that defines operations against Key-Value Store.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 */
public interface KeyValueStore<K, V> {

    /**
     * Inserts key-value pair into RocksDB.
     *
     * @param key of value.
     * @param value that should be persisted.
     * @throws SerializationException when it's not possible to serialize entity.
     * @throws SaveFailedException when it's not possible to persist entity.
     */
    void save(K key, V value) throws SerializationException, SaveFailedException;

    /**
     * Try to find value for a given key.
     *
     * @param key of entity that should be retrieved.
     * @return Optional of entity.
     * @throws SerDeException when it's not possible to serialize/deserialize entity.
     * @throws FindFailedException when it's not possible to retrieve a wanted entity.
     */
    Optional<V> findByKey(K key) throws SerDeException, FindFailedException;

    /**
     * Try to find all entities from repository.
     *
     * @return Collection of entities.
     * @throws DeserializationException when it's not possible to deserialize entity.
     */
    Collection<V> findAll() throws  DeserializationException;

    /**
     * Delete entity for a given key.
     *
     * @param key of entity that should be deleted.
     * @throws SerializationException when it's not possible to serialize entity.
     * @throws DeleteFailedException when it's not possible to delete a wanted entity.
     */
    void deleteByKey(K key) throws SerializationException, DeleteFailedException;

    /**
     * Deletes all entities from RocksDB.
     *
     * @throws DeleteAllFailedException when it's not possible to delete all entities.
     */
    void deleteAll() throws  DeleteAllFailedException;
}
