package main.java.inteliment.dao.api;

import feign.Headers;
import feign.RequestLine;

public interface ApiGetParagraphs {
	@RequestLine("GET /Rest/Paragraphs")
	@Headers({ "Content-Type: application/json"})
	String getParagraphs();
}
