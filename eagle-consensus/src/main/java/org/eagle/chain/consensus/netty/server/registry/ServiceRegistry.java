package org.eagle.chain.consensus.netty.server.registry;

import org.eagle.chain.consensus.netty.protocol.RpcServiceInfo;
import org.eagle.chain.consensus.netty.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务注册
 *
 * @author AapcheMoy
 */
public class ServiceRegistry {
    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);


    private List<String> pathList = new ArrayList<>();

    public ServiceRegistry(String registryAddress) {

    }

    public void registerService(String host, int port, Map<String, Object> serviceMap) {
        // Register service info
        List<RpcServiceInfo> serviceInfoList = new ArrayList<>();
        for (String key : serviceMap.keySet()) {
            String[] serviceInfo = key.split(ServiceUtil.SERVICE_CONCAT_TOKEN);
            if (serviceInfo.length > 0) {
                RpcServiceInfo rpcServiceInfo = new RpcServiceInfo();
                rpcServiceInfo.setServiceName(serviceInfo[0]);
                if (serviceInfo.length == 2) {
                    rpcServiceInfo.setVersion(serviceInfo[1]);
                } else {
                    rpcServiceInfo.setVersion("");
                }
                logger.info("Register new service: {} ", key);
                serviceInfoList.add(rpcServiceInfo);
            } else {
                logger.warn("Can not get service name and version: {} ", key);
            }
        }
    }

    public void unregisterService() {
        logger.info("Unregister all service");
        for (String path : pathList) {

        }
    }
}
