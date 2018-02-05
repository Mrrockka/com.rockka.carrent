package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.InvoiceDao;
import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.enums.InvoiceStatus;
import com.rockka.carrent.enums.Way;
import com.rockka.carrent.services.InvoiceService;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
 ** InvoiceDao proxy
 */
@Service("invoiceService")
public class InvoiceServiceImp implements InvoiceService {
    @Autowired
    private InvoiceDao invoiceDao;
    private Logger logger = LoggerFactory.getLogger(InvoiceServiceImp.class);
    /*
     ** Checks variables before dao call
     */
    @Override
    public Invoice getById(long id) {
        if(id > 0) {
            return invoiceDao.getById(id);
        }else {
            logger.error("Id is negative");
        }
        return null;
    }
    /*
     ** Overloaded method for convenience
     */
    @Override
    public List<Invoice> getAllWithUser(String username) {
        return getAllWithUser(username, InvoiceStatus.PENDING, Way.Equals);

    }
    /*
     ** Checks variables before dao call
     */
    @Override
    public List<Invoice> getAllWithUser(String username, InvoiceStatus status, Way way) {
        if(username != null && !username.equals("")){
            return invoiceDao.getAllWithUser(username, status, way);
        }else {
            logger.error("Username is null");
        }
        return null;
    }
    /*
    ** Checks variables before dao call
    */
    @Override
    public List<Invoice> getAllWithCar(long carId, InvoiceStatus status, Way way) {
        if(carId >0){
            return invoiceDao.getAllWithCar(carId, status, way);
        } else {
            logger.error("car_id is negative");
        }
        return null;
    }
    /*
     ** Overloaded method for convenience
     */
    @Override
    public List<Invoice> getAllWithCar(long carId) {
        return getAllWithCar(carId, InvoiceStatus.DENIED, Way.GreaterOrEquals);
    }
    /*
     ** Calling dao method
     */
    @Override
    public List<Invoice> getAll() {
        return invoiceDao.getAll();
    }
    /*
     ** Checks variables before dao call
     */
    @Override
    public Invoice save(Invoice invoice) {
        if(invoice != null){
            invoiceDao.save(invoice);
        }else {
            logger.error("invoice is null");
        }
        return invoice;
    }
    /*
     ** Checks variables and setts invoice status to deleted (Not deleting entity from DB)
     */
    @Override
    public Invoice delete(Invoice invoice) {
        if(invoice != null) {
            update(invoice.setStatus(InvoiceStatus.DELETED));
        } else {
            logger.error("invoice is null");
        }
        return invoice;
    }
    /*
     ** Checks variables before dao call
     */
    @Override
    public Invoice update(Invoice invoice){
        if(invoice != null) {
            invoiceDao.update(invoice.setModifiedAt(LocalDateTime.now()));
        }else {
            logger.error("invoice is null");
        }
        return invoice;
    }
}
