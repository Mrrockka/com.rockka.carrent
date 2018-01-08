package com.rockka.carrent;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.config.MvcConfig;
import com.rockka.carrent.config.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
//        implements WebApplicationInitializer
{
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
 /*   @Override
    public void onStartup(ServletContext servletContext) {
        //      root context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class,SecurityConfig.class);
//      add filter
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("springSecurityFilterChain",
                org.springframework.web.filter.DelegatingFilterProxy.class);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
        //protip: don't use context.refresh() it's cause an exception with resourceHandler
        servletContext.addListener(new ContextLoaderListener(rootContext));
        //      dispatcher context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(MvcConfig.class);


        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
                new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }*/
}
