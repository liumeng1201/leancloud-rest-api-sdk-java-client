package cn.leancloud.api;

import org.apache.log4j.Logger;

import cn.leancloud.api.exception.APIException;
import cn.leancloud.api.http.NativeHttpClient;
import cn.leancloud.api.http.ResponseWrapper;
import cn.leancloud.api.model.LCInstallation;
import cn.leancloud.api.model.gank.GankResponse;

import com.google.gson.Gson;

public class GankClient {
	private static final Logger LOG = Logger.getLogger(LCClient.class);
    public final static String API_URL = "http://gank.io/api/day/";

    private NativeHttpClient client;
    private Gson gson;

    public GankClient() {
        client = new NativeHttpClient(null, null);
        gson = new Gson();
    }

    public ResponseWrapper get(String path) throws APIException {
        String url = API_URL + path;
        return client.sendGet(url);
    }
    
    public GankResponse getPostsByDay(String day) throws APIException {
        ResponseWrapper res = get(day);
        return LCInstallation.fromResponse(res, GankResponse.class);
    }
}
