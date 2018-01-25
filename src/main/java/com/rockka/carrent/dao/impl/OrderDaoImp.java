package com.rockka.carrent.dao.impl;

import com.rockka.carrent.dao.OrderDao;
import com.rockka.carrent.domain.Car;
import com.rockka.carrent.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Transactional
@Repository("orderDao")
public class OrderDaoImp extends GenericDaoImp<Order> implements OrderDao {
    private Logger logger = LoggerFactory.getLogger(OrderDaoImp.class);

    public OrderDaoImp() {
        super(Order.class);
    }

    @Override
    public Order getById(long id) {
        Order order = null;
        try{
            order = (Order) getSession()
                    .createQuery("from Order where id =:id")
                    .setParameter("id", id)
                    .uniqueResult();
        }catch(Exception ex){
            logger.error("OrderDaoImp: getById " + ex);
        }
        return order;
    }

    @Override
    public List<Order> getAllWithUser(String username, boolean withDeleted){
        List<Order> orders = null;
        try{
            orders = getSession()
                    .createQuery("from Order where username  =:username")
                    .setParameter("username", username)
                    .list();
        } catch(Exception ex){
            logger.error("ORDER_DAO_IMP: getAllWithUser - query exception!!!" + ex);
        }
        return orders;
    }

    @Override
    public List<Order> getAllWithCar(long carId, boolean withDeleted){
        List<Order> orders = null;
        try{
            orders = getSession()
                    .createQuery("from Order where car_id  =:carId")
                    .setParameter("carId", carId)
                    .list();
        } catch(Exception ex){
            logger.error("ORDER_DAO_IMP: getAllWithCar - query exception!!!" + ex);
        }
        return orders;
    }

    @Override
    public Order getByIdWithUser(long orderId, String username) {
        Order order = null;
        try{
            order = (Order) getSession()
                    .createQuery("from Order where id =:orderId and username  =:username")
                    .setParameter("orderId", orderId)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch(Exception ex){
            logger.error("ORDER_DAO_IMP: getByIdWithUser - query exception!!!" + ex);
        }
        return order;
    }
}

