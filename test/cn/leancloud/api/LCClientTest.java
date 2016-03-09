package cn.leancloud.api;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import cn.leancloud.api.model.LCInstallation;

import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA. User: lidahe Date: 15/2/1 Time: 上午11:43 To change
 * this template use File | Settings | File Templates.
 */
public class LCClientTest {
	private static final Logger LOG = Logger.getLogger(LCClient.class);

	private static String id = "WnC3YL3NwMUyDyBu9BiTOaYb-gzGzoHsz";
	private static String key = "EychbyOykTnc938RriNG7F9J";
	protected LCClient client;
	private String token;
	private String objectId;

	@Before
	public void before() {
		client = new LCClient(id, key, false);

		token = "123321321123";
	}
	
	class UserItem {
		String deviceType;
		String deviceToken;
	}
	
	@Test
	public void getPostsFromGankIOAPI() throws Exception {
		UserItem item = new UserItem();
		item.deviceToken = token;
		item.deviceType = "ios";
		LCInstallation installation = client.installationsCreate(new Gson().toJson(item));
		LOG.debug(installation);
	}

	/*
	@Test
	public void installation() throws Exception {
		UserItem item = new UserItem();
		item.deviceToken = token;
		item.deviceType = "ios";
		LCInstallation installation = client.installationsCreate(new Gson().toJson(item));
		LOG.debug(installation);
	}

	@Test
	public void send_notification_alert() throws Exception {
		BaseResult result = client.sendNotificationAlertWithObjectId(
				"hello test world...", objectId);
		LOG.debug(result);
	}

	@Test
	public void send_notification_extras() throws Exception {
		Map data = new HashMap();
		data.put("alert", "show me");
		data.put("sound", "default");
		data.put("url", "http://leancloud.cn");
		data.put("type", "001");
		data.put("badge", "Increment");

		BaseResult result = client.sendNotificationObjectId(data, objectId);
		LOG.debug(result);
	}*/
}
