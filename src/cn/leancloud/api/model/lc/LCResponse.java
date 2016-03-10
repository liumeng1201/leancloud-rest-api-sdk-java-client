package cn.leancloud.api.model.lc;

import java.util.List;

public class LCResponse<T> {
	private List<T> results;

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
}
