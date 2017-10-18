package main.java.inteliment.bo.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import main.java.inteliment.dao.dao.IRestApiDao;
import main.java.inteliment.dao.model.SearchRequest;
import main.java.inteliment.dao.model.SearchResult;

@Component
public class CounterApiImp implements ICounterApiBo, ApplicationContextAware {

	@Autowired
	IRestApiDao dao;

	ApplicationContext context;

	@Override
	public SearchResult search(String endPoint, SearchRequest request) {
		String rawParagraph = dao.getDefaultParagraphs(endPoint);
		rawParagraph = rawParagraph.replaceAll("\\.", "").replaceAll("\\,", "").replaceAll("\\n", " ").toLowerCase();
		List<String> rawSearchWords = Arrays.asList(rawParagraph.split(" "));
		Map<String, Long> sortedGroupByResult = new LinkedHashMap<>();
		Map<String, Long> groupByResult = rawSearchWords.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		groupByResult.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
				.forEachOrdered(e -> sortedGroupByResult.put(e.getKey(), e.getValue()));
		System.out.println(sortedGroupByResult);
		SearchResult ret = context.getBean(SearchResult.class);
		List<Map<String, Long>> counts = new ArrayList<>();
		for (String key : request.getSearchText()) {
			if (-1 != Optional.ofNullable(sortedGroupByResult.get(key.toLowerCase())).orElse((long) -1)) {
				Map<String, Long> searchElement = new HashMap<>();
				searchElement.put(key, sortedGroupByResult.get(key.toLowerCase()));
				counts.add(searchElement);
			} else {
				Map<String, Long> searchElement = new HashMap<>();
				searchElement.put(key, (long) 0);
				counts.add(searchElement);
			}
		}
		ret.setCounts(counts);
		return ret;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.context = applicationContext;
	}

	@Override
	public List<Map<String, Long>> top(String endPoint, Integer topParam) {
		// TODO Auto-generated method stub
		if(topParam <= 0) {
			return new ArrayList<>();
		}
		List<Map<String, Long>> ret = new ArrayList<>();
		String rawParagraph = dao.getDefaultParagraphs(endPoint);
		rawParagraph = rawParagraph.replaceAll("\\.", "").replaceAll("\\,", "").replaceAll("\\n", " ").toLowerCase();
		List<String> rawSearchWords = Arrays.asList(rawParagraph.split(" "));
		Map<String, Long> sortedGroupByResult = new LinkedHashMap<>();
		Map<String, Long> groupByResult = rawSearchWords.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		groupByResult.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
				.forEachOrdered(e -> sortedGroupByResult.put(e.getKey(), e.getValue()));
		if (topParam <= groupByResult.size()) {
			int counter = 0;
			for (Map.Entry<String, Long> entry : sortedGroupByResult.entrySet()) {
				Map<String, Long> element = new HashMap<>();
				element.put(entry.getKey(), entry.getValue());
				ret.add(element);
//				System.out.println(entry.getKey() + "|" + entry.getValue());
				counter++;
				if(counter >= topParam)
					break;
			}
		}
		else {
			for (Map.Entry<String, Long> entry : sortedGroupByResult.entrySet()) {
				Map<String, Long> element = new HashMap<>();
				element.put(entry.getKey(), entry.getValue());
				ret.add(element);
//				System.out.println(entry.getKey() + "|" + entry.getValue());
			}
		}

		return ret;
	}

}
