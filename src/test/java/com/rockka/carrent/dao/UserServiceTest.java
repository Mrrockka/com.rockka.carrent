package com.rockka.carrent.dao;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.domain.User;
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

public class UserServiceTest extends Assert {

    private UserService userService;
    private UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Before
    public void startUp() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        userService = context.getBean("userService", UserService.class);
        userDao = context.getBean("userDao", UserDao.class);
    }

    @Test
    @Category(TransactionalTest.class)
    public void testSaveDelete() {
        User user;
        assertNotNull(
                user = userService.delete(
                        userService.save(
                                new User().setUsername("Superman").setPassword("superman").setRoles("ROLE_USER")
                                .setFirstName("Santa").setSecondName("Bremore").setAddress("LittleTinyOcean")
                                .setBirthday(new Date(91,1,1))
                        )
                )
        );
        testGetDetails();
        userDao.delete(user);
    }

    @Test
    @Category(BasicTest.class)
    public void testConection() {
        assertEquals("somebody", userService.getByUsername("somebody").getUsername());
    }

    @Test
    @Category(DetailTest.class)
    public void testGetDetails() {
        for (User user : userService.getAll()) {
            logger.warn(" nickname " + user.getUsername()
                    + " user role " + user.getRoles()
                    + " created at " + user.getCreatedAt()
                    + " modified at " + user.getModifiedAt()
                    + " status " + user.getUserStatus().toString()
            );
        }
    }
}
