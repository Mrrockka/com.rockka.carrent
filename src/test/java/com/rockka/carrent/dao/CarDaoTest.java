package com.rockka.carrent.dao;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.domain.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class CarDaoTest {
    public static void main(String [] args){
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
    }

    public static void showAll(ApplicationContext context){
        CarDao carDao = context.getBean("carDao", CarDao.class);
        for (Car car : carDao.getAll()){
            System.out.println("Car id is " + car.getId());
        }
    }

    public static void showById(ApplicationContext context){
        CarDao carDao = context.getBean("carDao", CarDao.class);
        Car car = carDao.getById(1);
        System.out.println("Car id is " + car.getId());
    }

    public static void save(Car car){

    }
    public static void delete(Car car){

    }

}
