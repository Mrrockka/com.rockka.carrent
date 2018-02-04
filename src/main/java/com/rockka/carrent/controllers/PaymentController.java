package com.rockka.carrent.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.enums.InvoiceStatus;
import com.rockka.carrent.services.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private InvoiceService invoiceService;

	private Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@RequestMapping("/approve")
	public String registerPayment(@RequestBody ObjectNode node){
		String ans = "failure";
		Invoice invoice = invoiceService.getById(node.get("invoice_id").asInt());
		try{
			invoiceService.update(invoice.setStatus(InvoiceStatus.ACTIVE));
		}catch(Exception ex){
			logger.error("" + ex);
		}

		return ans;
	}
}
