package org.eagle.chain.data.service;

import org.eagle.chain.data.model.AccountInfo;
import org.eagle.chain.data.service.impl.AccountInfoServiceImpl;

/**
 * @author ApacheMoy
 * @version create time：2023/1/11 14:50
 * @describe:
 */
public class ServiceFactory {
    private static AccountInfoService accountInfoService;

    public static AccountInfoService getAccountInfoService(){
        if(accountInfoService==null){
            accountInfoService=new AccountInfoServiceImpl();
        }
        return accountInfoService;
    }
}
