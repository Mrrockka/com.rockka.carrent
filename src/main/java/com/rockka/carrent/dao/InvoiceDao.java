package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.enums.InvoiceStatus;

import java.util.List;
/*
 ** Interface for database invoice selections
 */
//TODO: add interface with fields and rewrite getAllWithUser and getAllWithCar with relations to fields(-2 lt, -1 le, 0 eq etc)
public interface InvoiceDao extends GenericDao<Invoice> {
    /*
     ** Selecting invoice entity by id
     */
    public Invoice getById(long id);
    /*
     ** Selecting invoice entity by username
     */
    public List<Invoice> getAllWithUser(String username, InvoiceStatus status, int way);
    /*
     ** Selecting invoice entity by car id
     */
    public List<Invoice> getAllWithCar(long carId, InvoiceStatus status, int way);
}
