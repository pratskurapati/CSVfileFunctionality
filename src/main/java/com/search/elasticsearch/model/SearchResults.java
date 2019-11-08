package com.search.elasticsearch.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchResults<T> {
	private Integer total_results = 0;
	private List<T> results = new ArrayList<>();

	public SearchResults() {
	}

	public SearchResults(Integer total_results, List<T> results) {
		this.total_results = total_results;
		this.results = results;
	}

	public Integer getTotal_results() {
		return total_results;
	}

	public void setTotal_results(Integer total_results) {
		this.total_results = total_results;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

}
