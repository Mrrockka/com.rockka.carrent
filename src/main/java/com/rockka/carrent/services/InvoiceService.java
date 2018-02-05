package com.rockka.carrent.services;

import com.rockka.carrent.dao.InvoiceDao;
import com.rockka.carrent.domain.Invoice;

import java.util.List;

/*
 ** InvoiceDao proxy
 */
public interface InvoiceService extends InvoiceDao {
	/*
	** Overloaded method for convenience
	*/
	public List<Invoice> getAllWithUser(String username);
	/*
	 ** Overloaded method for convenience
	 */
	public List<Invoice> getAllWithCar(long carId);
}
