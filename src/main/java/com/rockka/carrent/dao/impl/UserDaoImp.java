package com.rockka.carrent.dao.impl;

import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Transactional
@Repository("userDao")
public class UserDaoImp extends GenericDaoImp<User> implements UserDao {
    private Logger logger = LoggerFactory.getLogger(UserDaoImp.class);
    public UserDaoImp(){super(User.class);}

    @Override
    public User getUserByNickname(String nickname) {
        User user = null;
        try {
            user = (User) getSession()
                    .createQuery("from User where nickname = :nickname and isDeleted = 0")
                    .setParameter("nickname", nickname)
                    .uniqueResult();
        } catch (Exception ex) {
            logger.error("User get by nickname " + ex);
        }
        return user;
    }
}
