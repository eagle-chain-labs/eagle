package org.eagle.chain.data.rocks.mapper;

public interface MapperFactory {

    static <T> JsonMapper<T> createFor(final Class<T> type) {
        return new JsonMapper<>(type);
    }
}
