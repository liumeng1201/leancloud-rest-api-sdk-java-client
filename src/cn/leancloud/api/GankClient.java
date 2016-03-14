package cn.leancloud.api;

import cn.leancloud.api.exception.APIException;
import cn.leancloud.api.http.NativeHttpClient;
import cn.leancloud.api.http.ResponseWrapper;
import cn.leancloud.api.model.gank.GankHistoryResponse;
import cn.leancloud.api.model.gank.GankPostsResponse;

import com.google.gson.Gson;

/**
 * 负责从gank.io网站获取数据
 * 
 * @author liumeng
 */
public class GankClient {
	private final String GANK_BASE_URL = "http://gank.io/api/day/";

	private NativeHttpClient client;
	private Gson gson;

	public GankClient() {
		client = new NativeHttpClient(null, null);
		gson = new Gson();
	}

	public ResponseWrapper get(String path) throws APIException {
		String url = GANK_BASE_URL + path;
		return client.sendGet(url);
	}

	public GankPostsResponse getPostsByDay(String day) throws APIException {
		ResponseWrapper res = get(day);
		return fromResponse(res, GankPostsResponse.class);
	}

	public GankHistoryResponse getPostsHistory() throws APIException {
		ResponseWrapper res = get("history");
		return fromResponse(res, GankHistoryResponse.class);
	}

	private <T> T fromResponse(ResponseWrapper responseWrapper, Class<T> clazz) {
		T result = null;

		if (responseWrapper.isServerResponse()) {
			String responseContent = responseWrapper.responseContent;
			/*
			responseContent = responseContent.replaceAll("_id", "postId");
			responseContent = responseContent.replaceAll("_ns", "ns");
			responseContent = responseContent.replaceAll("createdAt", "postTime");
			*/
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
