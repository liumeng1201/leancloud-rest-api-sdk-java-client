package cn.leancloud.api;


import java.util.Map;

import org.apache.log4j.Logger;

import cn.leancloud.api.exception.APIException;
import cn.leancloud.api.http.NativeHttpClient;
import cn.leancloud.api.http.ResponseWrapper;
import cn.leancloud.api.model.BaseResult;
import cn.leancloud.api.model.LCInstallation;
import cn.leancloud.api.push.PushPayload;

import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * User: lidahe
 * Date: 15/1/31
 * Time: 下午8:38
 * To change this template use File | Settings | File Templates.
 */
public class LCClient {
    private static final Logger LOG = Logger.getLogger(LCClient.class);
    private final static String API_URL = "https://leancloud.cn/1.1/";
    private final static String MODULE_INSTALLATIONS_PATH = "installations";
    private final static String MODULE_PUSH_PATH = "push";

    private String id;
    private String key;
    private NativeHttpClient client;
    private boolean apnsProduction = false;
    
    private Gson gson;

    public LCClient(String id, String key) {
        this(id, key, false);
    }

    public LCClient(String id, String key, boolean apnsProduction) {
        this.id = id;
        this.key = key;
        this.apnsProduction = apnsProduction;
        client = new NativeHttpClient(id, key);
        gson = new Gson();
    }

    public ResponseWrapper post(String path, Map data) throws APIException {
        String url = API_URL + path;
        String contont = gson.toJson(data);
        return client.sendPost(url, contont);
    }
    
    public ResponseWrapper post(String path, String data) throws APIException {
        String url = API_URL + path;
        return client.sendPost(url, data);
    }

    public ResponseWrapper put(String path, Map data) throws APIException {
        String url = API_URL + path;
        String contont = gson.toJson(data);
        return client.sendPost(url, contont);
    }

    public ResponseWrapper get(String path) throws APIException {
        String url = API_URL + path;
        return client.sendGet(url);
    }

    public ResponseWrapper delete(String path) throws APIException {
        String url = API_URL + path;
        return client.sendDelete(url);
    }

    public LCInstallation installationsCreate(Map data) throws APIException {
        ResponseWrapper res = post(MODULE_INSTALLATIONS_PATH, data);
        return LCInstallation.fromResponse(res, LCInstallation.class);
    }
    
    public LCInstallation installationsCreate(String data) throws APIException {
        ResponseWrapper res = post(MODULE_INSTALLATIONS_PATH, data);
        return LCInstallation.fromResponse(res, LCInstallation.class);
    }

    public BaseResult sendNotificationAlertWithObjectId(String alert, String objectId) throws APIException {
        PushPayload payload = PushPayload.newBuilder()
                .setProd(apnsProduction)
                .setAlert(alert)
                .setObjectId(objectId)
                .build();
        ResponseWrapper res = post(MODULE_PUSH_PATH, payload.toMap());
        return BaseResult.fromResponse(res, BaseResult.class);
    }

    public BaseResult sendNotificationAlertWithObjectId(String alert, Map data, String objectId) throws APIException {
        PushPayload payload = PushPayload.newBuilder()
                .setProd(apnsProduction)
                .setAlert(alert)
                .setData(data)
                .setObjectId(objectId)
                .build();
        ResponseWrapper res = post(MODULE_PUSH_PATH, payload.toMap());
        return BaseResult.fromResponse(res, BaseResult.class);
    }

    public BaseResult sendNotificationObjectId(Map data, String objectId) throws APIException {
        PushPayload payload = PushPayload.newBuilder()
                .setProd(apnsProduction)
                .setData(data)
                .setObjectId(objectId)
                .build();
        ResponseWrapper res = post(MODULE_PUSH_PATH, payload.toMap());
        return BaseResult.fromResponse(res, BaseResult.class);
    }

}
