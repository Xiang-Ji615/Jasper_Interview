package main.java.inteliment.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import main.java.inteliment.bo.bo.ICounterApiBo;
import main.java.inteliment.dao.model.SearchRequest;

@RestController
@RequestMapping(value="counter-api")
public class CounterApi {
	
	@Autowired
	Gson gson;
	
	@Autowired
	ICounterApiBo counterApiBo;

	@RequestMapping(value="search", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> search(HttpServletRequest request, @RequestBody(required=false) String searchRequest) {
		SearchRequest searchRequestModel;
		try {
			searchRequestModel = gson.fromJson(searchRequest, SearchRequest.class);
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body("The search param can't be converted, please double check the param");
		}
		return ResponseEntity.ok().body(counterApiBo.search(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(), searchRequestModel));
	}
	
	@RequestMapping(value="top/{topParam}", method=RequestMethod.GET, produces="text/csv", consumes = MediaType.ALL_VALUE)
	public Object top(HttpServletRequest request, @PathVariable(required=false) Integer topParam, HttpServletResponse response) throws IOException {
		 response.setContentType("text/csv; charset=utf-8");
		 String endPoint = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		 List<Map<String, Long>> retData = counterApiBo.top(endPoint, topParam);
		 if(topParam<=0) {
			 return ResponseEntity.badRequest().body("Please make sure the top param is greater than 0");
		 }
		 for(Map<String, Long> retElement : retData) {
				for (Map.Entry<String, Long> entry : retElement.entrySet())
					response.getWriter().println(entry.getKey() + "|" + entry.getValue());
		 }
		 return null;
	}
}
