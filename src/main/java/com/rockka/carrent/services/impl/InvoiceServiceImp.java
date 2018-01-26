package com.rockka.carrent.services.impl;

import com.rockka.carrent.dao.InvoiceDao;
import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("invoiceService")
public class InvoiceServiceImp implements InvoiceService {
    @Autowired
    private InvoiceDao invoiceDao;
    @Override
    public Invoice getById(long id) {
        if(id > 0) {
            return invoiceDao.getById(id);
        }
        return null;
    }

    @Override
    public List<Invoice> getAllWithUser(String username) {
        return getAllWithUser(username, false);

    }

    @Override
    public List<Invoice> getAllWithUser(String username, boolean withDeleted) {
        if(username != null && !username.equals("")){
            return invoiceDao.getAllWithUser(username, withDeleted);
        }
        return null;
    }

    @Override
    public List<Invoice> getAllWithCar(long carId, boolean withDeleted) {
        if(carId >0){
            invoiceDao.getAllWithCar(carId, withDeleted);
        }
        return null;
    }

    @Override
    public Invoice getByIdWithUser(long orderId, String username) {
        if(orderId>0 && username != null && !username.equals("")){
            return invoiceDao.getByIdWithUser(orderId, username);
        }
        return null;
    }

    @Override
    public List<Invoice> getAllWithCar(long carId) {
        return getAllWithCar(carId, false);
    }

    @Override
    public List<Invoice> getAll() {
        return invoiceDao.getAll();
    }

    @Override
    public Invoice save(Invoice invoice) {
        if(invoice != null){
            invoiceDao.save(invoice);
        }
        return invoice;
    }

    @Override
    public Invoice delete(Invoice invoice) {
        if(invoice != null) {
            update(invoice.setInvoiceStatus(0));
        }
        return invoice;
    }

    @Override
    public Invoice update(Invoice invoice){
        if(invoice != null) {
            invoiceDao.update(invoice.setModifiedAt(new Date()));
        }
        return invoice;
    }
}
