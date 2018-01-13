package com.rockka.carrent.dao;

import com.rockka.carrent.config.AppConfig;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Date;

public class UserDaoTest extends Assert {

    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Before
    public void startUp() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        userService = context.getBean("userService", UserService.class);
    }

    @Test
    @Ignore
    public void testSaveDelete() {
        assertNotNull(
                userService.delete(
                        userService.save(
                                new User().setNickname("Superman").setPassword("superman").setRoles("user")
                                .setFirstName("Santa").setSecondName("Bremore").setAddress("LittleTinyOcean")
                                .setBirthday(new Date(91,1,1))
                        )
                )
        );
    }

    @Test
    public void testConection() {
        assertEquals(1, userService.getUserByNickname("somebody").getId());
    }

    @Test
    @Ignore
    public void testGetDetails() {
        for (User user : userService.getAll()) {
            logger.debug(" nickname " + user.getNickname()
                    + " user role " + user.getRoles()
                    + " created at " + user.getCreatedAt()
                    + " modified at " + user.getModifiedAt()
                    + (user.getIsDeleted() == 1 ? " deleted" : " not deleted")
            );
        }
    }
}
