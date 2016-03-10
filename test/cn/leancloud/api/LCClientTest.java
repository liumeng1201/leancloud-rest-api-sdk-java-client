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

	class UserItem {
		String deviceType;
		String deviceToken;
	}

	@Test
	public void getPostsFromGankIOByDay() throws Exception {
		GankPostsResponse response = gankClient.getPostsByDay("2016/03/10");
		GankPostsResults posts = response.getResults();
		for (PostItem item : posts.Android) {
			String data = gson.toJson(item);
			LOG.debug(data);
			lcClient.addPost(data);
			Thread.sleep(50);
		}
	}

}
