package org.eagle.chain.data.mapper;

import lombok.Data;
import org.eagle.chain.data.model.AccountInfo;

import java.util.Map;

/**
 * @author ApacheMoy
 * @version create time：2023/1/11 10:16
 * @describe: this is a db factory
 */
public class MapperFactory {

    private static AccountInfoMapper accountInfoMapper;



    public static AccountInfoMapper getAccountInfoMapper(){
        if(accountInfoMapper==null){
            accountInfoMapper=new AccountInfoMapper();
        }
        return accountInfoMapper;
    }



}
