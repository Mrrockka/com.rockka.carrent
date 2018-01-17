package com.rockka.carrent.dao.impl;

import com.rockka.carrent.dao.OrderDao;
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
    public List<Order> getAll() {
        List<Order> orders = null;
        try{
            orders = getSession()
                    .createQuery("from Order")
                    .list();
        }catch(Exception ex){
            logger.error("OrderDaoImp: getAll " + ex);
        }
        return orders;
    }

    @Override
    public Order save(Order order) {
        if(order.getCreatedAt() == null){
            order.setCreatedAt(new Date()).setStatus("uncheked");
        }
        try{
            getSession()
                    .saveOrUpdate(order.setModifiedAt(new Date()));
        }catch(Exception ex){
            logger.error("OrderDaoImp: save " + ex);
        }
        return order;
    }

    @Override
    public Order delete(Order order) {
        order.setDeleted();
        update(order);
        return order;
    }

    @Override
    public Order update(Order order){
        try{
            order.setModifiedAt(new Date());
            update(order);
        }catch(Exception ex){
            logger.error("OrderDaoImp: update " +ex);
        }
        return order;
    }
}
