package com.rockka.carrent.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.rockka.carrent"})
@PropertySource("classpath:datasource.properties")
@EnableTransactionManagement
public class OrmConfig {
    @Value("${hibernate.connection.driver_class_name}")
    private String driverClassName;
    @Value("${hibernate.connection.url}")
    private String url;
    @Value("${hibernate.connection.username}")
    private String username;
    @Value("${hibernate.connection.password}")
    private String password;
    @Value("${hibernate.connection.scan_packages}")
    private String scanPackages;
    @Value("${hibernate.dialect}")
    private String dialect;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        return properties;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public SessionFactory sessionFactory(){
        return new LocalSessionFactoryBuilder(dataSource()).scanPackages(scanPackages)
                .addProperties(hibernateProperties()).buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory);
        return htm;
    }

}
