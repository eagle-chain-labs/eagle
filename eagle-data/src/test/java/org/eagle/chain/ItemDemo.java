package org.eagle.chain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author ApacheMoy
 * @version create time：2023/1/10 16:49
 * @describe:
 */
@Data
public class ItemDemo implements Serializable {
    private String address;
    private BigInteger balance;

    public ItemDemo(String address, BigInteger balance) {
        this.address = address;
        this.balance = balance;
    }

    public ItemDemo() {
    }
}
