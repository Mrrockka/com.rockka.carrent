package com.rockka.carrent.dao.impl;

import com.rockka.carrent.dao.InvoiceDao;
import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.enums.InvoiceStatus;
import com.rockka.carrent.enums.Way;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/*
 ** Class for database invoice selections
 */
@Transactional
@Repository("invoiceDao")
public class InvoiceDaoImp extends GenericDaoImp<Invoice> implements InvoiceDao {
    private Logger logger = LoggerFactory.getLogger(InvoiceDaoImp.class);

    public InvoiceDaoImp() {
        super(Invoice.class);
    }
    /*
     ** Selecting invoice entity by id
     */
    @Override
    public Invoice getById(long invoice_id) {
        Criteria criteria = getSession().createCriteria(Invoice.class);
        criteria.add(Restrictions.eq("id", invoice_id));
        return (Invoice) criteria.uniqueResult();
    }
    /*
     ** Selecting invoice entity by username
     */
    @Override
    public List<Invoice> getAllWithUser(String username, InvoiceStatus status, Way way){
        Criteria criteria = getSession().createCriteria(Invoice.class);
        criteria.createCriteria("user").add(Restrictions.eq("username", username));
        return criteria.list();
    }
    /*
     ** Selecting invoice entity by car id
     */
    @Override
    public List<Invoice> getAllWithCar(long car_id, InvoiceStatus status, Way way){
        Criteria criteria = getSession().createCriteria(Invoice.class);
        criteria.createCriteria("car").add(Restrictions.idEq(car_id));
        return criteria.list();
    }

}

