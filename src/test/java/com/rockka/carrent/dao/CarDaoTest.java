package com.rockka.carrent.dao;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.services.CarService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


public class CarDaoTest extends Assert {
    private static CarService carService;
    private Logger logger = LoggerFactory.getLogger(CarDaoTest.class);

    @Before
    public void startUp() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        carService = context.getBean("carService", CarService.class);
    }

    @Test
    @Ignore
    public void testSaveDelete() {
        assertNotNull(
                carService.delete(
                        carService.save(
                                new Car().setColor("blue").setDeleted().setName("Supercar")
                        )
                )
        );
    }

    @Test
    public void testConection() {
        assertEquals(1, carService.getById(1).getId());
    }

    @Test
    @Ignore
    public void testGetDetails() {
        for (Car car : carService.getAll()) {
            logger.info("id " + car.getId() + " color " + car.getColor() + " created at " + car.getCreatedAt() + " modified at " + car.getModifiedAt() + (car.getIsDeleted() == 'y' ? " deleted" : " not deleted"));
        }
    }
}
