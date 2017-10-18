package main.java.inteliment.dao.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultParagrapphsDaoImp implements IDefaultParagraphsDao {

	@Value(value="${detault.paragraphs:Not available}")
	String defaultParagraphs;
	
	@Override
	public String getDefaultParagraphs() {
		return defaultParagraphs;
	}

}
