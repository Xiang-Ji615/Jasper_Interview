package main.java.inteliment.dao.dao;

import org.springframework.stereotype.Component;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import main.java.inteliment.dao.api.AbstractIntelimentApi;
import main.java.inteliment.dao.api.ApiGetParagraphs;

@Component
public class RestApiDaoImp extends AbstractIntelimentApi implements IRestApiDao {

	@Override
	public String getDefaultParagraphs(String endPoint) {
		ApiGetParagraphs apiGetParagraphs = Feign.builder().requestInterceptor(new BasicAuthRequestInterceptor(username, password)).encoder(gsonEncoder).target(ApiGetParagraphs.class,endPoint);
		return apiGetParagraphs.getParagraphs();
	}

}
