package com.prgrms.catpicture.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
public class HeaderConfiguration {

	@Bean
	public RequestInterceptor requestInterceptor(@Value("${external.cat-service.key}") String key) {
		return requestTemplate -> requestTemplate.header("x-api-key", key);
	}
}
