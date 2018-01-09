package com.rockka.carrent;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.config.MvcConfig;
import com.rockka.carrent.config.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final Logger logger = LoggerFactory.getLogger(AppInitializer.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {AppConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters(){
        return new Filter[] {new DelegatingFilterProxy("springSecurityFilterChain")};
    }
}
