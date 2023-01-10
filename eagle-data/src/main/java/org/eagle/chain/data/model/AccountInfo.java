package org.eagle.chain.data.model;

import lombok.Data;
import org.eagle.chain.data.rocks.annotion.Database;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author ApacheMoy
 * @version create time：2023/1/10 16:49
 * @describe:
 */
@Data
@Database(name = "account_info")
public class AccountInfo implements Serializable {
    private String address;
    /**
     * coin balance
     */
    private BigInteger balance;
    /**
     * last update block
     */
    private BigInteger block;

    public AccountInfo(String address, BigInteger balance,BigInteger block) {
        this.address = address;
        this.balance = balance;
        this.block = block;
    }

    public AccountInfo() {
    }
}
