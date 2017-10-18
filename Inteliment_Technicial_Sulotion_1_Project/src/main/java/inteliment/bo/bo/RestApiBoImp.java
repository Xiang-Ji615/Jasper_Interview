package main.java.inteliment.bo.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.inteliment.dao.dao.IRestApiDao;

@Component
public class RestApiBoImp implements IRestApiBo {

	@Autowired
	IRestApiDao dao;
	
	@Override
	public String getDefaultParagraphs(String endPoint) {
		// TODO Auto-generated method stub
		return dao.getDefaultParagraphs(endPoint);
	}

}
