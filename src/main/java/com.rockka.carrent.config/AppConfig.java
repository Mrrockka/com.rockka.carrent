package com.rockka.carrent.config;


import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.dao.CarDaoImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.rockka.carrent"})
@Import({OrmConfig.class})
public class AppConfig {
    @Bean
    public CarDao carDao(){
        return new CarDaoImp();
    }

}
