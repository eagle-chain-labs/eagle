package org.eagle.chain.data.config;

import lombok.Data;

/**
 * @author ApacheMoy
 * @version create time：2023/1/11 11:24
 * @describe:
 */
public class RocksConfig {
    private static  String basePath;
    private static  Integer threadCount;

    public static void build(String path,Integer threadNum) {
        basePath=path;
        threadCount=threadNum;
    }

    public static String getBasePath(){
        return basePath;
    }
    public static Integer getThreadCount(){
        return threadCount;
    }
}
