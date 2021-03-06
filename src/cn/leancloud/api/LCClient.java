package cn.leancloud.api;

import cn.leancloud.api.exception.APIException;
import cn.leancloud.api.http.NativeHttpClient;
import cn.leancloud.api.http.ResponseWrapper;
import cn.leancloud.api.model.gank.PostItem;
import cn.leancloud.api.model.lc.LCResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 负责对leancloud服务进行读写查询操作
 * 
 * @author liumeng
 */
public class LCClient {
	private final String API_URL = "https://leancloud.cn/1.1/";
	private final String MODULE_ADD_POST_PATH = "classes/GankPosts";
	private final String MODULE_GET_POST_PATH = "classes/GankPosts/";
	private final String MODULE_UPDATE_POST_PATH = "classes/GankPosts/";
	private final String MODULE_QUERY_POST_PATH = "classes/GankPosts?";
	private final String MODULE_DEL_POST_PATH = "classes/GankPosts/";
	private final String MODULE_GET_ALL_POST_PATH = "classes/GankPosts";
	
	private final String MODULE_BATCH_PATH = "batch";
	public static final String MODULE_MULTIREQUEST_ADD_PATH = "/1.1/classes/GankPosts";

	private NativeHttpClient client;
	private Gson gson;

	public LCClient(String id, String key) {
		this(id, key, false);
	}

	public LCClient(String id, String key, boolean apnsProduction) {
		client = new NativeHttpClient(id, key);
		gson = new Gson();
	}

	public ResponseWrapper post(String path, String data) throws APIException {
		String url = API_URL + path;
		return client.sendPost(url, data);
	}

	public ResponseWrapper put(String path, String data) throws APIException {
		String url = API_URL + path;
		return client.sendPut(url, data);
	}

	public ResponseWrapper get(String path) throws APIException {
		String url = API_URL + path;
		return client.sendGet(url);
	}

	public ResponseWrapper delete(String path) throws APIException {
		String url = API_URL + path;
		return client.sendDelete(url);
	}

	/**
	 * 添加post
	 */
	public PostItem addPost(String data) throws APIException {
		ResponseWrapper res = post(MODULE_ADD_POST_PATH, data);
		return fromResponse(res, PostItem.class);
	}

	/**
	 * 获取post
	 */
	public PostItem getPost(String objectId) throws APIException {
		ResponseWrapper res = get(MODULE_GET_POST_PATH + objectId);
		return fromResponse(res, PostItem.class);
	}

	/**
	 * 更新post
	 */
	public PostItem updatePost(String objectId, String data) throws APIException {
		ResponseWrapper res = put(MODULE_UPDATE_POST_PATH + objectId, data);
		return fromResponse(res, PostItem.class);
	}

	/**
	 * 删除post
	 */
	public PostItem delPost(String objectId) throws APIException {
		ResponseWrapper res = delete(MODULE_DEL_POST_PATH + objectId);
		return fromResponse(res, PostItem.class);
	}

	/**
	 * 获取所有的posts
	 */
	public LCResponse<PostItem> getAllPosts() throws APIException {
		ResponseWrapper res = get(MODULE_GET_ALL_POST_PATH);
		return gson.fromJson(res.responseContent, new TypeToken<LCResponse<PostItem>>() {}.getType());
	}

	/**
	 * 根据条件查询Post列表
	 * 
	 * @param where
	 *            查询条件
	 */
	public LCResponse<PostItem> getPostsByWhere(String where) throws APIException {
		ResponseWrapper res = get(MODULE_QUERY_POST_PATH + "where=" + where);
		return gson.fromJson(res.responseContent, new TypeToken<LCResponse<PostItem>>() {}.getType());
	}
	
	/**
	 * 批量操作
	 */
	public String MultiOpt(String data) throws APIException {
		ResponseWrapper res = post(MODULE_BATCH_PATH, data);
		return res.responseContent;
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
