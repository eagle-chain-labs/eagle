package org.eagle.chain.consensus.config;

/**
 * @author ApacheMoy
 * @version create time：2023/1/13 19:14
 * @describe:
 */
public class ConsensusConfig {
    /**
     * server port
     */
    public static int P2P_PORT=8056;

    /**
     * server bind ip
     */
    public static String BIND_IP="localhost";

    /**
     *  Maximum number of peer nodes
     */
    public static int MAX_NUMBER_OF_PEE_NODES=5;

    /**
     * Maximum number of broadcast nodes
     */
    public static int MAX_NUMBER_OF_BROADCAST_NODES=5;
}
