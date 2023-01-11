package org.eagle.chain.data.rocks.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eagle.chain.data.config.RocksConfig;
import org.eagle.chain.data.rocks.annotion.Database;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.nio.file.Files.createDirectories;

/**
 * Class responsible for communication with RocksDB.
 */
public abstract class RocksDBConnection<K, V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocksDBConnection.class);

    protected RocksDB rocksDB;

    public RocksDBConnection() {
        RocksDB.loadLibrary();

        try {
            final Options options = new Options().setCreateIfMissing(true);
            String dbName=extractValueType().getAnnotation(Database.class).name();
            final String rocksDirectory = RocksConfig.getBasePath()+dbName;
            final Path path = Paths.get(rocksDirectory);
            createDirectories(path);
            rocksDB = RocksDB.open(options, path.toString());
        } catch (final Exception exception) {
            LOGGER.error("Exception occurred during RocksDB initialization. Shutting down application...");
            System.exit(1);
        }
    }
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
