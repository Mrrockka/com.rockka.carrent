package com.rockka.carrent.dao.impl;

import com.rockka.carrent.dao.InvoiceDao;
import com.rockka.carrent.domain.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository("invoiceDao")
public class InvoiceDaoImp extends GenericDaoImp<Invoice> implements InvoiceDao {
    private Logger logger = LoggerFactory.getLogger(InvoiceDaoImp.class);

    public InvoiceDaoImp() {
        super(Invoice.class);
    }

    @Override
    public Invoice getById(long invoice_id) {
        Invoice invoice = null;
        try{
            invoice = (Invoice) getSession()
                    .createQuery("from Invoice where invoice_id =:invoice_id")
                    .setParameter("invoice_id", invoice_id)
                    .uniqueResult();
        }catch(Exception ex){
            logger.error("InvoiceDaoImp: getById " + ex);
        }
        return invoice;
    }

    @Override
    public List<Invoice> getAllWithUser(String username, boolean withDeleted){
        List<Invoice> orders = null;
        try{
            orders = getSession()
                    .createQuery("from Invoice where username  =:username")
                    .setParameter("username", username)
                    .list();
        } catch(Exception ex){
            logger.error("Invoice_DAO_IMP: getAllWithUser - query exception!!!" + ex);
        }
        return orders;
    }

    @Override
    public List<Invoice> getAllWithCar(long car_id, boolean withDeleted){
        List<Invoice> orders = null;
        try{
            orders = getSession()
                    .createQuery("from Invoice where car_id  =:car_id")
                    .setParameter("car_id", car_id)
                    .list();
        } catch(Exception ex){
            logger.error("Invoice_DAO_IMP: getAllWithCar - query exception!!!" + ex);
        }
        return orders;
    }

    @Override
    public Invoice getByIdWithUser(long invoice_id, String username) {
        Invoice invoice = null;
        try{
            invoice = (Invoice) getSession()
                    .createQuery("from Invoice where invoice_id =:invoice_id and username  =:username")
                    .setParameter("invoice_id", invoice_id)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch(Exception ex){
            logger.error("Invoice_DAO_IMP: getByIdWithUser - query exception!!!" + ex);
        }
        return invoice;
    }
}

