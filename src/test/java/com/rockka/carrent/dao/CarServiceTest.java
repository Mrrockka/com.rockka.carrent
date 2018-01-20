package com.rockka.carrent.dao;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.test_categories.BasicTest;
import com.rockka.carrent.test_categories.DetailTest;
import com.rockka.carrent.test_categories.TransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class CarServiceTest extends Assert {
    private static CarService carService;
    private CarDao carDao;
    private Logger logger = LoggerFactory.getLogger(CarServiceTest.class);

    @Before
    public void startUp() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        carService = context.getBean("carService", CarService.class);
        carDao = context.getBean("carDao", CarDao.class);
    }

    @Test
    @Category(TransactionalTest.class)
    @DisplayName("test Save delete operations")
    public void testSaveDelete() {
        Car car;
        assertNotNull(
                car = carService.delete(
                        carService.save(
                                new Car().setColor("blue").setDeleted().setName("Supercar")
                        )
                )
        );
        testGetAll();
        carDao.delete(car);
    }

    @Test
    @Category(BasicTest.class)
    @DisplayName("test connection to DB")
    public void testConection() {
        assertEquals(1, carService.getById(1).getId());
    }

    @Test
    @Category(DetailTest.class)
    @DisplayName("test get all operations")
    public void testGetAll() {
        for (Car car : carService.getAll()) {
            logger.warn("id " + car.getId() + " color " + car.getColor() + " created at " + car.getCreatedAt() + " modified at " + car.getModifiedAt() + (car.getIsDeleted() == 'y' ? " deleted" : " not deleted"));
        }
    }
}
