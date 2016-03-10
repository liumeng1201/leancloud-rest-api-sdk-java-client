package cn.leancloud.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import cn.leancloud.api.http.IHttpClient.RequestMethod;
import cn.leancloud.api.model.gank.GankPostsResponse;
import cn.leancloud.api.model.gank.GankPostsResults;
import cn.leancloud.api.model.gank.PostItem;
import cn.leancloud.api.model.lc.MultiRequest;
import cn.leancloud.api.model.lc.MultiRequestItem;

import com.google.gson.Gson;

public class LCClientTest {
	private static final Logger LOG = Logger.getLogger(LCClient.class);

	private static String id = "WnC3YL3NwMUyDyBu9BiTOaYb-gzGzoHsz";
	private static String key = "EychbyOykTnc938RriNG7F9J";

	protected LCClient lcClient;
	protected GankClient gankClient;

	private Gson gson;

	@Before
	public void before() {
		lcClient = new LCClient(id, key, false);
		gankClient = new GankClient();

		gson = new Gson();
	}

	/*
	@Test
	public void getPostsFromGankIOByDay() throws Exception {
		GankPostsResponse response = gankClient.getPostsByDay("2016/03/10");
		GankPostsResults posts = response.getResults();
		for (PostItem item : posts.iOS) {
			String data = gson.toJson(item);
			lcClient.addPost(data);
		}
	}*/
	
	@Test
	public void addMultiPost() throws Exception {
		// 一次添加多个post
		GankPostsResponse response = gankClient.getPostsByDay("2016/03/10");
		GankPostsResults posts = response.getResults();
		String data = gson.toJson(opt(posts.iOS));
		String result = lcClient.MultiOpt(data);
		LOG.debug(result);
	}
	
	private MultiRequest<PostItem> opt(List<PostItem> datas) {
		List<MultiRequestItem<PostItem>> requests = new ArrayList<MultiRequestItem<PostItem>>();
		for (PostItem data : datas) {
			MultiRequestItem<PostItem> item = new MultiRequestItem<PostItem>();
			item.setMethod(RequestMethod.POST.name());
			item.setPath(LCClient.MODULE_MULTIREQUEST_ADD_PATH);
			item.setBody(data);
			requests.add(item);
		}
		MultiRequest<PostItem> request = new MultiRequest<PostItem>();
		request.setRequests(requests);
		return request;
	}
	
	/*
	@Test
	public void updatePostsFromLCByobjectId() throws Exception {
		PostItem item = lcClient.getPost("56e1253a5bbb50004cd3d776");
		item.setWho("123qwe");
		String updateContent = gson.toJson(item);
		item = lcClient.updatePost("56e1253a5bbb50004cd3d776", updateContent);
	}

	@Test
	public void getAllPostFromLC() throws Exception {
		LCResponse<PostItem> results = lcClient.getAllPosts();
		LOG.debug("getAllPostFromLC ~ " + results.getResults().size());
	}

	@Test
	public void getPostFromLCByWhere() throws Exception {
		PostItem item = new PostItem();
		item.setType("Android");
		String where = gson.toJson(item);
		LOG.debug(where);
		LCResponse<PostItem> results = lcClient.getPostsByWhere(where);
		LOG.debug("getAllPostFromLC ~ " + results.getResults().size());
	}
	 */
}
