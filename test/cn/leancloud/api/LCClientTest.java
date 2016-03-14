package cn.leancloud.api;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.javalite.activejdbc.Base;
import org.junit.Before;
import org.junit.Test;

import cn.leancloud.api.model.activejdbc.GankPost;
import cn.leancloud.api.model.activejdbc.MakeInstrumentationUtil;
import cn.leancloud.api.model.gank.GankHistoryResponse;
import cn.leancloud.api.model.gank.GankPostsResponse;
import cn.leancloud.api.model.gank.GankPostsResults;
import cn.leancloud.api.model.gank.PostItem;
import cn.leancloud.api.utils.ListUtils;

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
	public void findPostFromDB() throws Exception {
		MakeInstrumentationUtil.make();
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "root", "rootroot");
		
		List<GankPost> result = GankPost.findAll();
		List<PostItem> posts = new ArrayList<PostItem>();
		for (GankPost item : result) {
			PostItem pi = new PostItem();
			pi.setId(item.getString(GankPostTable._ID));
			pi.setNs(item.getString(GankPostTable._NS));
			pi.setCreatedAt(item.getString(GankPostTable.CREATEAT));
			pi.setDesc(item.getString(GankPostTable.DESCRIPTION));
			pi.setPublishedAt(item.getString(GankPostTable.PUBLISHEDAT));
			pi.setUrl(item.getString(GankPostTable.URL));
			pi.setType(item.getString(GankPostTable.TYPE));
			pi.setWho(item.getString(GankPostTable.WHO));
			posts.add(pi);
		}
		LOG.debug(posts.size());
	}*/

	@Test
	public void addPostToDB() throws Exception {
		MakeInstrumentationUtil.make();
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://db4free.net/gankapp", "liumeng1201", "liumeng1201");
		// Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "root", "rootroot");
		
		GankHistoryResponse response = gankClient.getPostsHistory();
		for (String day : response.getResults()) {
			getDataByDay(day);
			Thread.sleep(1000);
		}
	}
	
	private void getDataByDay(String day) throws Exception {
		List<GankPost> result = GankPost.where("day = ?", day);
		if (!ListUtils.isEmpty(result)) {
			if (result.size() > 0) {
				// day天数据已经存在
				LOG.debug(day + " already exist"); 
				return;
			}
		}
		
		GankPostsResponse response = gankClient.getPostsByDay(day.replaceAll("-", "/"));
		GankPostsResults posts = response.getResults();
		saveData(posts.Android, day);
		Thread.sleep(500);
		saveData(posts.iOS, day);
		Thread.sleep(500);
		saveData(posts.前端, day);
		Thread.sleep(500);
		saveData(posts.休息视频, day);
		Thread.sleep(500);
		saveData(posts.拓展资源, day);
		Thread.sleep(500);
		saveData(posts.福利, day);
	}
	
	private void saveData(List<PostItem> datas, String day) throws SQLException {
		if (ListUtils.isEmpty(datas)) {
			return;
		}
		
		PreparedStatement ps = Base.startBatch("insert into gank_posts (_id, _ns, createdAt, description, publishedAt, type, url, who, day) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		for (PostItem item : datas) {
			Base.addBatch(ps, item.getId(), item.getNs(), item.getCreatedAt(), item.getDesc(), item.getPublishedAt(), item.getType(), item.getUrl(), item.getWho(), day);
		}
		Base.executeBatch(ps);
		ps.close();
	}
}
