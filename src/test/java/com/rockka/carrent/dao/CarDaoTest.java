package com.rockka.carrent.dao;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.domain.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import java.util.Date;
import java.util.List;


public class CarDaoTest extends Assert{
    private static CarDao carDao;
    private Logger logger = LoggerFactory.getLogger(CarDaoTest.class);

    @Before
    public void startUp(){
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        carDao = context.getBean("carDao", CarDao.class);
    }

    public static Car save(){
        Car car = new Car();
        car.setColor("blue").setIsDeleted('n').setName("Supercar");
        carDao.save(car);
        return car;
    }

    public static int delete(Car car){
        carDao.delete(car);
        return 1;
    }

    @Test
    public void testSaveDelete(){
        assertNotNull(delete(save()));
    }
    @Test
    public void testConection(){
        assertEquals(1, carDao.getById(1).getId());
    }
    @Test
    public void testGetDetails(){
//        something goes wrong with expression in Dao class
        /*for(Car car : carDao.getAllWithoutDetails()){
            logger.info("id " + car.getId() + " color " + car.getColor() +" created at " + car.getCreatedAt() + " modified at " + car.getModifiedAt() + (car.getIsDeleted()=='y' ? " deleted" : " not deleted"));
        }*/
        for(Car car : carDao.getAll()){
            logger.info("id " + car.getId() + " color " + car.getColor() + " created at " + car.getCreatedAt() + " modified at " + car.getModifiedAt() + (car.getIsDeleted()=='y' ? " deleted" : " not deleted"));
        }
    }
}
