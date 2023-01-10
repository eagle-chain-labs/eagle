package org.eagle.chain;

import lombok.extern.slf4j.Slf4j;
import org.eagle.chain.common.crypto.Crypto;
import org.junit.Test;


/**
 * @author ApacheMoy
 * @version create time：2023/1/10 12:23
 * @describe:
 */
@Slf4j
public class AccountTest {
    @Test
    public void testAddress(){
        String mnemonic= Crypto.generateMnemonic();
        String pri=Crypto.generatePrivateKeyFromMnemonic(mnemonic);
        String address=Crypto.generateAddressFromPriv(pri);
        String pub=Crypto.generatePubKeyHexFromPriv(pri);
        log.info("mnemonic:{}",mnemonic);
        log.info("pri:{}",pri);
        log.info("address:{}",address);
        log.info("pub:{}",pub);
    }
}
