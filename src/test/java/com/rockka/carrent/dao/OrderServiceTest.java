package com.rockka.carrent.dao;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.Order;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.OrderService;
import com.rockka.carrent.services.UserService;
import com.rockka.carrent.test_categories.BasicTest;
import com.rockka.carrent.test_categories.DetailTest;
import com.rockka.carrent.test_categories.TransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Date;

public class OrderServiceTest extends Assert{

    private OrderService orderService;
    private CarService carService;
    private UserService userService;
    private CarDao carDao;
    private UserDao userDao;
    private OrderDao orderDao;
    private Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);
    @Before
    public void startUp() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        orderService = context.getBean("orderService", OrderService.class);
        userService = context.getBean("userService", UserService.class);
        carService = context.getBean("carService", CarService.class);
        userDao = context.getBean("userDao", UserDao.class);
        carDao = context.getBean("carDao", CarDao.class);
        orderDao = context.getBean("orderDao", OrderDao.class);
    }

    @Test
    @Category(TransactionalTest.class)
    public void testSaveDelete(){
        Car car = new Car()
                .setName("Batmobile v1000")
                .setCountry("Mexico")
                .setColor("Blackest")
                .setPrice(9999)
                .setReleaseDate(new Date(1939, 12,02));
        carService.save(car);

        User user = new User()
                .setRoles("ROLE_USER")
                .setUsername("Batman")
                .setPassword("bat")
                .setFirstName("Bruce")
                .setSecondName("Wayne")
                .setBirthday(new Date(1930, 03, 01))
                .setAddress("Gotham");
        userService.save(user);

        Order order = new Order()
                .setCar(car)
                .setUser(user)
                .setDescription("Everyday rent")
                .setPrice(1000000000)
                .setStartsAt(new Date(1939,12,03))
                .setExpiresAt(new Date(2100, 12,03));

        assertNotNull(
                orderService.delete(
                        orderService.save(order)
                )
        );
        testGetAll();
        orderDao.delete(order);
        carDao.delete(car);
        userDao.delete(user);
    }

    @Test
    @Category(BasicTest.class)
    public void testConnection(){
        assertEquals(1, orderService.getById(1).getId());
    }

    @Test
    @Category(DetailTest.class)
    public void testGetAll(){
        for (Order o: orderService.getAll()){
            logger.warn(
                    "Order id: " + o.getId()
                    + "| car id: " + o.getCar().getId()
                    + "| user nickname: " + o.getUser().getUsername()
                    + "| description: " + o.getDescription()
                    + "| price: " + o.getPrice()
                    + "| starts at: " + o.getStartsAt()
                    + "| expires at: " + o.getExpiresAt()
                    + "| status: " + o.getStatus()
            );
        }
    }
}
