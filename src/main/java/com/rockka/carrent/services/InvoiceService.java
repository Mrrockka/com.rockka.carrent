package com.rockka.carrent.services;

import com.rockka.carrent.dao.InvoiceDao;
import com.rockka.carrent.domain.Invoice;

import java.util.List;

public interface InvoiceService extends InvoiceDao {
	public List<Invoice> getAllWithUser(String username);
	public List<Invoice> getAllWithCar(long carId);
}
