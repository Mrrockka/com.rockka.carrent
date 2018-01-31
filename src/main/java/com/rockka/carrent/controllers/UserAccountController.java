package com.rockka.carrent.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rockka.carrent.domain.Invoice;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.InvoiceService;
import com.rockka.carrent.services.UserService;
import com.rockka.carrent.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//TODO: change java.util.Date to JodaTime
@Controller
@RequestMapping("/user")
public class UserAccountController extends UserUtil {
    private Logger logger = LoggerFactory.getLogger(UserAccountController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/account")
    public String account(Model model){
        return "user/account";
    }

    @GetMapping("/info")
    @ResponseBody
    public JsonNode userInfo() {
        ObjectNode node = mapper.createObjectNode();
        User user = userService.getByUsername(getPrincipal().getUsername());

        node.put("username", user.getUsername())
                .put("firstname", user.getFirstName())
                .put("secondname", user.getSecondName())
                .put("lastname", user.getLastName())
                .put("address", user.getAddress())
                .put("about_me", user.getAboutMe())
                .put("birthday", user.getBirthday().toString());

        return node;
    }
//TODO: test it
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(@RequestBody ObjectNode node){
        String ans = "failure";
        User user = userService.getByUsername(getPrincipal().getUsername());
        if(user != null){
            userService.update(
                    user.setFirstName(node.get("firstname").asText())
                            .setSecondName(node.get("secondname").asText())
                            .setLastName(node.get("lastname").asText())
                            .setAddress(node.get("address").asText())
                            .setAboutMe(node.get("about_me").asText())
                            .setBirthday(new Date(node.get("birthday").asLong()))
            );
            ans = "success";
        }
        return ans;
    }

    @GetMapping("/invoice/show_all")
    @ResponseBody
    public JsonNode showInvoices(){
        ArrayNode node = mapper.createArrayNode();
        List<Invoice> invoices = invoiceService.getAllWithUser(getPrincipal().getUsername());
        for(Invoice invoice : invoices){
            node.addObject()
                    .put("invoice_id", invoice.getId())
                    .put("car_name", invoice.getCar().getName())
                    .put("starts_at", invoice.getStartsAt().toString())
                    .put("expires_at", invoice.getExpiresAt().toString())
                    .put("invoice_price", invoice.getPrice())
                    .put("invoiceStatus", invoice.getInvoiceStatus().toString());
        }
        return node;
    }

    @GetMapping("/invoice/{id}")
    @ResponseBody
    public JsonNode showInvoiceById(@PathVariable("id") long id){
        ObjectNode node = mapper.createObjectNode();
        String username = getPrincipal().getUsername();
        Invoice invoice = invoiceService.getByIdWithUser(id, username);

        node.put("invoice_id", invoice.getId())
                .put("username", username)
                .put("car_name", invoice.getCar().getName())
                .put("invoice_price", invoice.getPrice())
                .put("starts_at", invoice.getStartsAt().toString())
                .put("expires_at", invoice.getExpiresAt().toString())
                .put("description", invoice.getDescription())
                .put("invoiceStatus", invoice.getInvoiceStatus().toString())
                .put("status", invoice.getInvoiceStatus().toInt());

        return node;
    }

    @RequestMapping("/invoice/update/{id}/status/{status}")
    @ResponseBody
    public String updateInvoice(@PathVariable("id") long id, @PathVariable("status") int status){
        String ans = "failure";
        try{
            Invoice invoice = invoiceService.getById(id).setInvoiceStatus(status);
            invoiceService.update(invoice);
            ans = "Success";
        }catch(Exception ex){
            logger.error("UPDATE_ORDER: " + ex);
        }
        return ans;
    }



}

