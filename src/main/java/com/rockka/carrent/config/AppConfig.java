package com.rockka.carrent.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OrmConfig.class})
@ComponentScan(basePackages = {"com.rockka.carrent.services"})
public class AppConfig {

}
