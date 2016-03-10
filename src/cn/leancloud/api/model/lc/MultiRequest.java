package cn.leancloud.api.model.lc;

import java.util.List;

public class MultiRequest<T> {
	private List<MultiRequestItem<T>> requests;

	public List<MultiRequestItem<T>> getRequests() {
		return requests;
	}

	public void setRequests(List<MultiRequestItem<T>> requests) {
		this.requests = requests;
	}
}
