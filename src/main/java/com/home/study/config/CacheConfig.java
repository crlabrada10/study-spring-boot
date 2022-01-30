package com.home.study.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author crlab
 *
 */
@Configuration
@EnableCaching
public class CacheConfig {
	
	/**
	 * Esto es un administrador de cache y va administrar todo lo que este dentro }
	 * de ConcurrentMapCacheManager y se va a cachear en la pc
	 * */
	@Bean
	public CacheManager getManager() {
		return new ConcurrentMapCacheManager("users");
	}
}
