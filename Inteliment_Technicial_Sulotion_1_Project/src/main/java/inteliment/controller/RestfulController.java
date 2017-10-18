package main.java.inteliment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.java.inteliment.bo.bo.IDefaultParagraphsBo;
import main.java.inteliment.bo.bo.IRestApiBo;

@RestController
@RequestMapping(value="Rest")
public class RestfulController {
	
	@Autowired
	IDefaultParagraphsBo bo;
	
	@Autowired
	IRestApiBo restBo;

	@RequestMapping(value="Paragraphs")
	public Object getDefaultParagraphs() {
		return bo.getDefaultParagraphs();
	}
	
	@RequestMapping(value="ParagraphsApi")
	public Object getDefaultParagraphs(HttpServletRequest request) {
		return restBo.getDefaultParagraphs(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort());
	}

}
