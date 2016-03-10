package cn.leancloud.api.model.gank;

import java.util.List;

public class GankPostsResponse {
	private List<String> category;
	private boolean error;
	private GankPostsResults results;

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

	public GankPostsResults getResults() {
		return results;
	}

	public void setResults(GankPostsResults results) {
		this.results = results;
	}
}
