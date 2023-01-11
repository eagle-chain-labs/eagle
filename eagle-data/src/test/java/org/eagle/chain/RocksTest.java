package org.eagle.chain;

import lombok.extern.slf4j.Slf4j;
import org.eagle.chain.data.config.RocksConfig;
import org.eagle.chain.data.rocks.core.RocksDBConfiguration;
import org.eagle.chain.data.rocks.core.exception.FindFailedException;
import org.eagle.chain.data.rocks.core.exception.SaveFailedException;
import org.eagle.chain.data.rocks.mapper.exception.DeserializationException;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


/**
 * @author ApacheMoy
 * @version create time：2023/1/10 16:39
 * @describe:
 */
@Slf4j
public class RocksTest {
    @Test
    public void testSave() throws SaveFailedException {
        RocksConfig.build("D:\\data\\rocks\\rocksdb\\account",5);
        final ItemDemoRepository itemRepository = new ItemDemoRepository();
        final ItemDemo item = new ItemDemo("0x123", new BigInteger("1000000"));
        itemRepository.save(item.getAddress(), item);
    }

    @Test
    public void testGet() throws FindFailedException, DeserializationException {
        RocksConfig.build("D:\\data\\rocks\\rocksdb\\account",5);

        final ItemDemoRepository itemRepository = new ItemDemoRepository();
        final Optional<ItemDemo> itemOptional = itemRepository.findByKey("0x123");
        final Collection<ItemDemo> all = itemRepository.findAll();
        //itemRepository.deleteByKey(item.getAddress());
        //itemRepository.deleteAll();

    }
}
