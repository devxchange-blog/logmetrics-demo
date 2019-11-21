package io.devxchange.logmetrics.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.apache.log4j.Logger;

/**
 * Created by io.devxchange
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	private static final Logger logger = Logger.getLogger(SwaggerConfig.class);

	@Bean
	public Docket api() {
		if (logger.isDebugEnabled()) {
			logger.debug("entering api()");
			logger.debug("exiting api()");
		}
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("io.oneclicklabs.demo.controller"))
				.paths(PathSelectors.any()).build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (logger.isDebugEnabled()) {
			logger.debug("entering addResourceHandlers(ResourceHandlerRegistry)");
			logger.debug("registry: " + registry);
		}
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		if (logger.isDebugEnabled()) {
			logger.debug("exiting addResourceHandlers()");
		}
	}
}
