package com.rockka.carrent.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OrmConfig.class})
public class AppConfig {

}
