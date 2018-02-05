package com.rockka.carrent.controllers;

import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.enums.InvoiceStatus;
import com.rockka.carrent.services.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/*
** Controller for payment operations
*/
@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private InvoiceService invoiceService;

	private Logger logger = LoggerFactory.getLogger(PaymentController.class);
	/*
	** Approving payment
	*/
	@RequestMapping("/approve/{invoice_id}")
	@ResponseBody
	public String registerPayment(@PathVariable("invoice_id") long invoice_id){
		String ans = "failure";
		Invoice invoice = invoiceService.getById(invoice_id);
		try{
			invoiceService.update(invoice.setStatus(InvoiceStatus.ACTIVE));
		}catch(Exception ex){
			logger.error("" + ex);
		}

		return ans;
	}
	/*
	** Return payment view
	*/
	@GetMapping("/get_form/{invoice_id}")
	public String getPaymentForm(@PathVariable("invoice_id") long invoice_id, Model model){
		Invoice invoice = invoiceService.getById(invoice_id);
		model.addAttribute("invoice_id", invoice.getId())
				.addAttribute("username", invoice.getUser().getUsername())
				.addAttribute("car_name", invoice.getCar().getName())
				.addAttribute("invoice_price", invoice.getPrice());
		return "user/payment";
	}
}
