package cn.leancloud.api;


import java.util.Map;

import cn.leancloud.api.exception.APIException;
import cn.leancloud.api.http.NativeHttpClient;
import cn.leancloud.api.http.ResponseWrapper;
import cn.leancloud.api.model.gank.PostItem;

import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * User: lidahe
 * Date: 15/1/31
 * Time: 下午8:38
 * To change this template use File | Settings | File Templates.
 */
public class LCClient {
    private final static String API_URL = "https://leancloud.cn/1.1/";
    private final static String MODULE_ADD_POST_PATH = "classes/GankPosts";

    private NativeHttpClient client;
    private Gson gson;

    public LCClient(String id, String key) {
        this(id, key, false);
    }

    public LCClient(String id, String key, boolean apnsProduction) {
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

    public PostItem addPost(String data) throws APIException {
        ResponseWrapper res = post(MODULE_ADD_POST_PATH, data);
        return fromResponse(res, PostItem.class);
    }
    
    private <T> T fromResponse(ResponseWrapper responseWrapper, Class<T> clazz) {
		T result = null;

		if (responseWrapper.isServerResponse()) {
			String responseContent = responseWrapper.responseContent;
			result = gson.fromJson(responseContent, clazz);
		} else {
			try {
				result = clazz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
