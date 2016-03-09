package cn.leancloud.api;

import org.apache.log4j.Logger;

import cn.leancloud.api.exception.APIException;
import cn.leancloud.api.http.NativeHttpClient;
import cn.leancloud.api.http.ResponseWrapper;
import cn.leancloud.api.model.LCInstallation;

import com.google.gson.Gson;

public class GankClient {
	private static final Logger LOG = Logger.getLogger(LCClient.class);
    public final static String API_URL = "https://leancloud.cn/1.1/";

    private NativeHttpClient client;
    private Gson gson;

    public GankClient() {
        client = new NativeHttpClient(null, null);
        gson = new Gson();
    }
    
    public ResponseWrapper post(String path, String data) throws APIException {
        String url = API_URL + path;
        return client.sendPost(url, data);
    }

    public ResponseWrapper put(String path, String data) throws APIException {
        String url = API_URL + path;
        return client.sendPost(url, data);
    }

    public ResponseWrapper get(String path) throws APIException {
        String url = API_URL + path;
        return client.sendGet(url);
    }

    public ResponseWrapper delete(String path) throws APIException {
        String url = API_URL + path;
        return client.sendDelete(url);
    }
    
    public LCInstallation installationsCreate(String data) throws APIException {
        ResponseWrapper res = post(MODULE_INSTALLATIONS_PATH, data);
        return LCInstallation.fromResponse(res, LCInstallation.class);
    }
}
