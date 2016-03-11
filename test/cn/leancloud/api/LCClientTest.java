package cn.leancloud.api;

import java.util.List;

import org.apache.log4j.Logger;
import org.javalite.activejdbc.Base;
import org.junit.Before;
import org.junit.Test;

import cn.leancloud.api.model.test.Employee;
import cn.leancloud.api.model.test.MakeInstrumentationUtil;

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
	
	@Test
	public void dbTest() throws Exception {
		MakeInstrumentationUtil.make();
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "root", "rootroot");
		Employee e = new Employee();
		e.set("first_name", "TestTest");
		e.set("last_name", "Doe");
		e.saveIt();
		
		List<Employee> employees = Employee.findAll();
		LOG.debug(employees.size());
	}

	/*
	@Test
	public void getPostsFromGankIOByDay() throws Exception {
		LCResponse<PostItem> results = lcClient.getAllPosts();
		LOG.debug("getAllPostFromLC ~ " + results.getResults().size());
		
		/*
		GankHistoryResponse response = gankClient.getPostsHistory();
		for (String day : response.getResults()) {
			day = day.replaceAll("-", "/");
			addMultiPostByDay(day);
			Thread.sleep(10000);
		}
	}*/
	
	/*
	// 一次添加多个post
	public void addMultiPostByDay(String day) throws APIException {
		GankPostsResponse response = gankClient.getPostsByDay(day);
		GankPostsResults posts = response.getResults();
		LOG.debug(lcClient.MultiOpt(gson.toJson(opt(posts.Android))));
		LOG.debug(lcClient.MultiOpt(gson.toJson(opt(posts.iOS))));
		LOG.debug(lcClient.MultiOpt(gson.toJson(opt(posts.前端))));
		LOG.debug(lcClient.MultiOpt(gson.toJson(opt(posts.休息视频))));
		LOG.debug(lcClient.MultiOpt(gson.toJson(opt(posts.拓展资源))));
		LOG.debug(lcClient.MultiOpt(gson.toJson(opt(posts.福利))));
	}
	
	private MultiRequest<PostItem> opt(List<PostItem> datas) {
		if (ListUtils.isEmpty(datas)) {
			return null;
		}
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
	*/
	
	/*
	@Test
	public void updatePostsToLCByobjectId() throws Exception {
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
