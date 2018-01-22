package com.rockka.carrent.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OrmConfig.class})
@ComponentScan(basePackages = {"com.rockka.carrent.services"})
public class AppConfig {
}
