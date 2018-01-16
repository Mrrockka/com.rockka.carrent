package com.rockka.carrent.dao;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.Order;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.OrderService;
import com.rockka.carrent.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Date;

public class OrderServiceTest extends Assert{

    private OrderService orderService;
    private CarService carService;
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);
    @Before
    public void startUp() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        orderService = context.getBean("orderService", OrderService.class);
        userService = context.getBean("userService", UserService.class);
        carService = context.getBean("carService", CarService.class);
    }

    @Test
    @Ignore
    public void testSaveDelete(){
        Car car = new Car()
                .setName("Batmobile v1000")
                .setCountry("Mexico")
                .setColor("Blackest")
                .setPrice(9999)
                .setReleaseDate(new Date(1939, 12,02))
                .setCreatedAt(new Date())
                .setDeleted();
        carService.save(car);

        User user = new User()
                .setRoles("ROLE_USER")
                .setNickname("Batman")
                .setPassword("bat")
                .setFirstName("Bruce")
                .setSecondName("Wayne")
                .setBirthday(new Date(1930, 03, 01))
                .setAddress("Gotham")
                .setCreatedAt(new Date())
                .setDeleted();
        userService.save(user);
        Order order = new Order()
                .setCar(car)
                .setUser(user)
                .setDescription("Everyday rent")
                .setPrice(1000000000)
                .setStartsAt(new Date(1939,12,03))
                .setExpiresAt(new Date(2100, 12,03))
                .setStatus("active");

        assertNotNull(
                orderService.delete(
                        orderService.save(order)
                )
        );
        testGetAll();
    }

    @Test
    public void testConnection(){
        assertEquals(1, orderService.getById(1).getId());
    }

    @Test
    @Ignore
    public void testGetAll(){
        for (Order o: orderService.getAll()){
            System.out.println(
                    "Order id: " + o.getId()
                    + "| car id: " + o.getCar().getId()
                    + "| user nickname: " + o.getUser().getNickname()
                    + "| description: " + o.getDescription()
                    + "| price: " + o.getPrice()
                    + "| starts at: " + o.getStartsAt()
                    + "| expires at: " + o.getExpiresAt()
                    + "| status: " + o.getStatus()
            );
        }
    }
}
