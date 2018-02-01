package com.rockka.carrent.service;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.test_categories.BasicTest;
import com.rockka.carrent.test_categories.DetailTest;
import com.rockka.carrent.test_categories.TransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {
                AppConfig.class
        })
public class CarServiceTest extends Assert {

    @Autowired
    private CarDao carDao;
    @Autowired
    private CarService carService;
    private Logger logger = LoggerFactory.getLogger(CarServiceTest.class);

    @Before
    public void startUp() {
    }

    @Test
    @Category(TransactionalTest.class)
    @DisplayName("test Save delete operations")
    public void testSaveDelete() {
        Car car;
        assertNotNull(
                car = carService.delete(
                        carService.save(
                                new Car().setColor("blue").setStatus(0).setName("Supercar")
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
        logger.info("\nCarServiceTest: TEST_GET_ALL\n");
        for (Car car : carService.getAll()) {
            logger.info("id " + car.getId() + " color " + car.getColor() + " created at " + car.getCreatedAt() + " modified at " + car.getModifiedAt() + " status " + car.getStatus().toString());
        }
    }
}
