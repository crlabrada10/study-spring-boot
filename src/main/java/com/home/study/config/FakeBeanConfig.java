package com.home.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

/**
 * @author crlabrada
 *
 */
@Configuration
public class FakeBeanConfig {
	
	@Bean
	public Faker getFaker() {
		return new Faker();
	}
}
