package main.java.inteliment.dao.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public abstract class AbstractIntelimentApi {

	@Autowired
	protected GsonEncoder gsonEncoder;

	@Autowired
	protected GsonDecoder gsonDecoder;

	@Value("${api.username:optus}")
	protected String username;

	@Value("${api.password:candidates}")
	protected String password;
	
}
