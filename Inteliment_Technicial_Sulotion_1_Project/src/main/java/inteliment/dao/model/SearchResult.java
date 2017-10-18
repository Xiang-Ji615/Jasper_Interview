package main.java.inteliment.dao.model;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Component
@Scope("prototype")
public class SearchResult {

	@SerializedName("counts")
	@Expose
	private List<Map<String, Long>> counts;

	public List<Map<String, Long>> getCounts() {
		return counts;
	}

	public void setCounts(List<Map<String, Long>> counts) {
		this.counts = counts;
	}

}
