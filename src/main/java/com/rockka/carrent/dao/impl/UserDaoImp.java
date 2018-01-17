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

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        try {
            users = getSession()
                    .createQuery("from User where isDeleted = 0")
                    .list();
        } catch (Exception ex) {
            logger.error("User getAll " + ex);
        }
        return users;
    }

    @Override
    public User save(User user) {
        if(user.getCreatedAt() == null){
            user.setCreatedAt(new Date()).setIsDeleted(0);
        }
        try {
            getSession()
                    .saveOrUpdate(user.setModifiedAt(new Date()));
        } catch (Exception ex) {
            user.setCreatedAt(null);
            logger.error("User save " + ex);
        }
        return user;
    }

    @Override
    public User delete(User user) {
        save(user.setDeleted());
        return user;
    }
}
