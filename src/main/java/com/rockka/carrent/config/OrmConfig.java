package com.rockka.carrent.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.rockka.carrent.dao"
        , "com.rockka.carrent.domain"
})
@PropertySource("classpath:datasource.properties")
public class OrmConfig {
    private final Logger logger = LoggerFactory.getLogger(OrmConfig.class);
    @Value("${hibernate.connection.driver_class}")
    private String driverClassName;
    @Value("${hibernate.connection.url}")
    private String url;
    @Value("${hibernate.connection.username}")
    private String username;
    @Value("${hibernate.connection.password}")
    private String password;
    @Value("${hibernate.scan_packages}")
    private String [] scanPackages;
    @Value("${hibernate.dialect}")
    private String dialect;

    @Bean
    public DataSource dataSource(){
        logger.debug("ORM: In datasource");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties(){
        logger.debug("ORM: in properties");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        return properties;
    }

    @Bean
    @SuppressWarnings("deprecation")
    public SessionFactory sessionFactory(){
        logger.debug("ORM: in session factory");
        return new LocalSessionFactoryBuilder(dataSource()).scanPackages(scanPackages)
                .addProperties(hibernateProperties()).buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        logger.debug("ORM: in transaction manager");
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory);
        return htm;
    }

}
