package com.rockka.carrent.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.rockka.carrent"})
@Import({OrmConfig.class})
public class AppConfig {


}
