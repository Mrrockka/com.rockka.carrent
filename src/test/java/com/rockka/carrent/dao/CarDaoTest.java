package com.rockka.carrent.dao;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.domain.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import java.util.Date;
import java.util.List;

public class CarDaoTest {
    private static CarDao carDao;

    public static void main(String [] args){
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        carDao = context.getBean("carDao", CarDao.class);
        Car car = save();
        showById(car.getId());
        delete(car.getId());
    }

    public static List<Car> showAll(){
        List<Car> cars = carDao.getAll();
        for (Car car : cars){
            System.out.println("Car id is " + car.getId());
        }
        return cars;
    }

    public static Car showById(long id){
        Car car = carDao.getById(id);
        System.out.println("Car id: " + car.getId());
        return car;
    }

    public static Car save(){
        Car car = new Car();
        car.setColor("blue").setCreatedAt(new Date()).setModifiedAt(new Date()).setIsDeleted('n').setName("Supercar");
        carDao.save(car);
        showAll();
        return car;
    }

    public static void delete(long id){
        carDao.delete(showById(id));
        showAll();
    }

}
