package main.java.inteliment.bo.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.java.inteliment.dao.dao.IDefaultParagraphsDao;

@Component
public class DefaultParagraphsBoImp implements IDefaultParagraphsBo {
	
	@Autowired
	IDefaultParagraphsDao dao;

	@Override
	public String getDefaultParagraphs() {
		return dao.getDefaultParagraphs();
	}

}
