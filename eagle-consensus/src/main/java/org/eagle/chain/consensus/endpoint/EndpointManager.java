package org.eagle.chain.consensus.endpoint;

import com.alipay.sofa.jraft.entity.PeerId;
import com.alipay.sofa.jraft.util.Endpoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eagle.chain.consensus.config.ConsensusConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ApacheMoy
 * @version create time：2023/1/14 10:15
 * @describe:
 */
@Slf4j
public class EndpointManager {
    private static boolean isCreateServer=false;

    private static Map<String,PeerId> p2pNode=new ConcurrentHashMap<>();

    /**
     * Create P2P communication service and return to start status
     * @return
     */
    public static boolean createServer(){
        if(isCreateServer){
            return true;
        }
        Endpoint addr = new Endpoint(ConsensusConfig.BIND_IP, ConsensusConfig.P2P_PORT);
        String s = addr.toString();
        PeerId peer = new PeerId();
        boolean success = peer.parse(s);
        isCreateServer=success;
        log.info("create p2p server ip:{},port:{},status:{}",ConsensusConfig.BIND_IP, ConsensusConfig.P2P_PORT,success);

        return success;
    }

    /**
     * PeerId represents a participant of the raft protocol (leader/follower/validate etc.),
     * It consists of three elements: ip: port: index,
     * IP is the IP of the node,
     * Port is the port,
     * The index represents the serial number of the same port, which is 0 by default.
     * This field is reserved to support the same port to start different raft nodes, which are distinguished by index.
     * @return
     */
    public static PeerId createPeer(String peeIp,int peePort){
        if(StringUtils.isEmpty(peeIp)||peePort==0){
            log.error("add pee format error");
            return null;
        }
        PeerId peerId=p2pNode.get(peeIp+peePort);
        if(peerId!=null){
            return peerId;
        }
        PeerId peer = new PeerId(peeIp, peePort);
        String s = peer.toString();
        boolean success = peer.parse(s);
        if(success){
            p2pNode.put(peeIp+peePort,peer);
            log.info("add peer {}:{} success",peeIp,peePort);
            return peer;
        }else {
            log.error("add peer {}:{} error",peeIp,peePort);
            return null;
        }
    }
}
