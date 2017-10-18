package main.java.inteliment.dao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

@Configuration
public class BeanConfig {

	@Bean
	public GsonEncoder getGsonEncoder() {
		return new GsonEncoder();
	}
	
	@Bean
	public GsonDecoder getGsonDecoder() {
		return new GsonDecoder();
	}
	
	@Bean
	public Gson getGson() {
		return new Gson();
	}
}
