package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.enums.InvoiceStatus;

import java.util.List;
//TODO: add interface with fields and rewrite getAllWithUser and getAllWithCar with relations to fields(-2 lt, -1 le, 0 eq etc)
public interface InvoiceDao extends GenericDao<Invoice> {
    public Invoice getById(long id);
    public List<Invoice> getAllWithUser(String username, InvoiceStatus status, int way);
    public List<Invoice> getAllWithCar(long carId, InvoiceStatus status, int way);
}
