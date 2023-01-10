package org.eagle.chain.data.mapper;

import org.eagle.chain.data.model.AccountInfo;
import org.eagle.chain.data.rocks.core.KVStore;
import org.eagle.chain.data.rocks.core.RocksDBConfiguration;

/**
 * @author ApacheMoy
 * @version create time：2023/1/10 16:50
 * @describe:
 */
public class AccountInfoMapper extends KVStore<String, AccountInfo> {
    public AccountInfoMapper(final RocksDBConfiguration configuration) {
        super(configuration);
    }
}
