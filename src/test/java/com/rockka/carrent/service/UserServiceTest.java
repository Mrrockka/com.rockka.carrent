package com.rockka.carrent.service;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.config.OrmConfig;
import com.rockka.carrent.config.MvcConfig;
import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.UserService;
import com.rockka.carrent.test_categories.BasicTest;
import com.rockka.carrent.test_categories.DetailTest;
import com.rockka.carrent.test_categories.TransactionalTest;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {
                AppConfig.class
        })
public class UserServiceTest extends Assert {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Before
    public void startUp() {
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
                                .setBirthday(new LocalDate(91,1,1))
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
