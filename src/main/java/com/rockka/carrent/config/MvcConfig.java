package com.rockka.carrent.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
// Spring MVC configuration
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
		"com.rockka.carrent.controllers"
})
public class MvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	private final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
	private ApplicationContext context;
	/*
	 **  This bean used to create json-nodes and send them with server response
	 */
	@Bean
	public ObjectMapper mapper() {
		return new ObjectMapper();
	}
	/*
	 **  Need for Thymeleaf work
	 */
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		logger.debug("MVCCONFIG: In templateResolver");
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.context);
		templateResolver.setPrefix("/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(true);
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	/*
	 **  Need for Thymeleaf work
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		logger.debug("MVCCONFIG: In templateEngine");
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}
	/*
	 **  Need for Thymeleaf work
	 */
	@Bean
	public ThymeleafViewResolver viewResolver() {
		logger.debug("MVCCONFIG: In viewResolver");
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}
	/*
	** Handler to map request addresses with resource locations
	*/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.debug("MVCCONFIG: In resourceRegistry");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/thumbs/**").addResourceLocations("/thumbnails/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	}

//	Overriding ApplicationContextAware method
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

//    TODO: exception handler configuration
}