package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Invoice;

import java.util.List;
//TODO: add interface with fields and rewrite getAllWithUser and getAllWithCar with relations to fields(-2 lt, -1 le, 0 eq etc)
public interface InvoiceDao extends GenericDao<Invoice> {
    public Invoice getById(long id);
    public List<Invoice> getAllWithUser(String username, boolean withDeleted);
    public List<Invoice> getAllWithCar(long carId, boolean withDeleted);
}
