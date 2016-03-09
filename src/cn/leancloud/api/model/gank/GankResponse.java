package cn.leancloud.api.model.gank;

import java.util.List;

public class GankResponse {
	private List<String> category;
	private boolean error;
	private GankResponseResults results;

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public GankResponseResults getResults() {
		return results;
	}

	public void setResults(GankResponseResults results) {
		this.results = results;
	}
}
