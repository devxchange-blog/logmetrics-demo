package io.devxchange.logmetrics.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import io.devxchange.logmetrics.interceptor.RestPayloadInterceptor;
import io.devxchange.logmetrics.writer.LogWriterManager;

/**
 * Created by io.devxchange
 */
@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {


	@Autowired
	@Qualifier("manager.logwriter")
	private LogWriterManager logWriterManager;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RestPayloadInterceptor(logWriterManager));
	}

}
