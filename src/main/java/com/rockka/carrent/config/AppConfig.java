package com.rockka.carrent.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.dao.impl.CarDaoImp;
import com.rockka.carrent.dao.impl.UserDaoImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OrmConfig.class})
public class AppConfig {
    private final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    @Bean
    public CarDao carDao(){
        logger.debug("APPCONFIG: in carDao");
        return new CarDaoImp();
    }
    @Bean
    public ObjectMapper objectMapper(){
        logger.debug("APPCONFIG: in objectMapper");
        return new ObjectMapper();
    }

    @Bean
    public UserDao userDao(){
        logger.debug("APPCONFIG: in userDao");
        return new UserDaoImp();
    }
}
