package org.eagle.chain.common.base;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Data
public class EnvBase {

    protected String mainPrefix;
    protected String denom;
    protected String chainID;
    protected String hdPath;
    protected String validatorAddrPrefix;
    protected String pubPrefix;
    protected String accountUrlPath;

    public EnvBase() {
        this.mainPrefix = "ec";
        this.denom = "ec";
        this.chainID = "2286";
        this.hdPath = "M/44H/2286H/0H/0/1";
        this.accountUrlPath = "/accounts/";
    }

}
