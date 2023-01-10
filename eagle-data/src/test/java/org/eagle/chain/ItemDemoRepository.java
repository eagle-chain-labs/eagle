package org.eagle.chain;

import org.eagle.chain.data.rocks.core.AsyncKVStore;
import org.eagle.chain.data.rocks.core.RocksDBConfiguration;

/**
 * @author ApacheMoy
 * @version 创建时间：2023/1/10 16:50
 * @describe:
 */
public class ItemDemoRepository extends AsyncKVStore<String, ItemDemo> {
    public ItemDemoRepository(final RocksDBConfiguration configuration) {
        super(configuration);
    }
}
