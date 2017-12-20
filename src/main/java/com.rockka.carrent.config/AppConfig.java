package com.rockka.carrent.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.dao.CarDaoImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.rockka.carrent"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        })
@Import({OrmConfig.class})
public class AppConfig {
    private final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    @Bean
    public CarDao carDao(){
        logger.info("APPCONFIG: in carDao");
        return new CarDaoImp();
    }
    @Bean
    public ObjectMapper objectMapper(){
        logger.info("APPCONFIG: in objectMapper");
        return new ObjectMapper();
    }
}
