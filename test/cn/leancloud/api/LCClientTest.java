package cn.leancloud.api;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import cn.leancloud.api.model.gank.GankPostsResponse;
import cn.leancloud.api.model.gank.GankPostsResults;
import cn.leancloud.api.model.gank.PostItem;

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
		for (PostItem item : posts.Android) {
			String data = gson.toJson(item);
			lcClient.addPost(data);
			Thread.sleep(50);
		}
	}*/
	
	@Test
	public void updatePostsFromLCByobjectId() throws Exception {
		PostItem item = lcClient.getPost("56e1253a5bbb50004cd3d776");
		LOG.debug("getPostsFromLCByobjectId ~ " + gson.toJson(item));
		item.setWho("123qwe");
		String updateContent = gson.toJson(item);
		item = lcClient.updatePost("56e1253a5bbb50004cd3d776", updateContent);
		LOG.debug("updatePostsFromLCByobjectId ~ " + gson.toJson(item));
	}

}
