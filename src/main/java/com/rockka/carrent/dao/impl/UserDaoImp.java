package com.rockka.carrent.dao.impl;

import com.rockka.carrent.dao.UserDao;
import com.rockka.carrent.domain.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Transactional
@Repository("userDao")
public class UserDaoImp extends GenericDaoImp<User> implements UserDao {
    private Logger logger = LoggerFactory.getLogger(UserDaoImp.class);
    public UserDaoImp(){super(User.class);}

    @Override
    public User getByUsername(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        return (User) criteria.uniqueResult();
    }
}
