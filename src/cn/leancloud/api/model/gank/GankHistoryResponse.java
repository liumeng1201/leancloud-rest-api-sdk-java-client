package cn.leancloud.api.model.gank;

import java.util.List;

public class GankHistoryResponse {
	private boolean error;
	private List<String> results;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public List<String> getResults() {
		return results;
	}

	public void setResults(List<String> results) {
		this.results = results;
	}
}
