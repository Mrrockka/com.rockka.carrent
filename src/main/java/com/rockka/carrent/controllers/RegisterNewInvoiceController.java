package com.rockka.carrent.controllers;

import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.enums.InvoiceStatus;
import com.rockka.carrent.services.CarService;
import com.rockka.carrent.services.InvoiceService;
import com.rockka.carrent.services.UserService;
import com.rockka.carrent.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invoice")
public class RegisterNewInvoiceController extends UserUtil{
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    private Logger logger = LoggerFactory.getLogger(RegisterNewInvoiceController.class);

    @GetMapping("/car/{car_id}")
    public String getInvoiceForCar(@PathVariable("car_id") long id, Model model){
        User user =userService.getByUsername((getPrincipal()).getUsername());
        String username = user.getFirstName() + " " + user.getSecondName();
        username += user.getLastName() != null ? " " +user.getLastName() : "";
        model.addAttribute("username", username);
        model.addAttribute("user_address", user.getAddress());
        model.addAttribute("user_birthday", user.getBirthday());
        model.addAttribute("car", carService.getById(id));
        return "user/register_new_invoice";
    }

    @PostMapping("/save/{car_id}")
	@ResponseBody
    public String save(@RequestBody Invoice invoice, @PathVariable("car_id") long carId, Model model){
        invoice.setCar(carService.getById(carId));
        invoice.setUser(userService.getByUsername(getPrincipal().getUsername()));
        invoice.setStatus(InvoiceStatus.NOT_PAID);
        invoiceService.save(invoice);

        return "" + invoice.getId();
    }

}
