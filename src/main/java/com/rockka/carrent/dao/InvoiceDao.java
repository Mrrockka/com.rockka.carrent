package com.rockka.carrent.dao;

import com.rockka.carrent.domain.Invoice;

import java.util.List;

public interface InvoiceDao extends GenericDao<Invoice> {
    public Invoice getById(long id);
    public List<Invoice> getAllWithUser(String username, boolean withDeleted);
    public List<Invoice> getAllWithCar(long carId, boolean withDeleted);
    public Invoice getByIdWithUser(long orderId, String username);
}
