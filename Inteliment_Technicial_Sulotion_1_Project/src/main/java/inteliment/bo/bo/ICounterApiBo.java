package main.java.inteliment.bo.bo;

import java.util.List;
import java.util.Map;

import main.java.inteliment.dao.model.SearchRequest;
import main.java.inteliment.dao.model.SearchResult;

public interface ICounterApiBo {

	public SearchResult search(String endPoint, SearchRequest request);
	
	public List<Map<String, Long>> top(String endPoint, Integer topParam);
}
