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
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
** Working with user view(user/account) and script(js/create_user_account)
*/
@RestController
@RequestMapping("/user")
public class UserAccountController extends UserUtil {
    private Logger logger = LoggerFactory.getLogger(UserAccountController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ObjectMapper mapper;
    /*
    ** Creating json node with user info and send it with response
    */
    @GetMapping("/info")
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
    /*
     ** Update operation for user info
     */
    @RequestMapping("/update")
    public String updateUser(@RequestBody ObjectNode node){
        String ans = "failure";
        User user = userService.getByUsername(getPrincipal().getUsername());
        try {
            userService.update(
                    user.setFirstName(node.get("firstname").asText())
                            .setSecondName(node.get("secondname").asText())
                            .setLastName(node.get("lastname").asText())
                            .setAddress(node.get("address").asText())
                            .setAboutMe(node.get("about_me").asText())
                            .setBirthday(new LocalDate(node.get("birthday").asText()))
            );
            ans = "success";
        }catch(Exception ex){
            logger.error("" +ex);
        }
        return ans;
    }
    /*
     ** Creating json node with every user invoices info and send it with response
     */
    @GetMapping("/invoice/show_all")
    public JsonNode showInvoices(){
        ArrayNode node = mapper.createArrayNode();
        List<Invoice> invoices = invoiceService.getAllWithUser(getPrincipal().getUsername());
        for(Invoice invoice : invoices){
            node.addObject()
                    .put("invoice_id", invoice.getId())
                    .put("car_name", invoice.getCar().getName())
                    .put("starts_at", invoice.getStartsAt().toString("yyyy-MM-dd : kk-mm"))
                    .put("expires_at", invoice.getExpiresAt().toString("yyyy-MM-dd : kk-mm"))
                    .put("invoice_price", invoice.getPrice())
                    .put("invoiceStatus", invoice.getStatus().toString());
        }
        return node;
    }
    /*
     ** Creating json node with user invoice info and send it with response
     */
    @GetMapping("/invoice/{id}")
    public JsonNode showInvoiceById(@PathVariable("id") long id){
        ObjectNode node = mapper.createObjectNode();
        Invoice invoice = invoiceService.getById(id);

        node.put("invoice_id", invoice.getId())
                .put("username", invoice.getUser().getUsername())
                .put("car_name", invoice.getCar().getName())
                .put("invoice_price", invoice.getPrice())
                .put("starts_at", invoice.getStartsAt().toString("yyyy-MM-dd : kk-mm"))
                .put("expires_at", invoice.getExpiresAt().toString("yyyy-MM-dd : kk-mm"))
                .put("description", invoice.getDescription())
                .put("invoiceStatus", invoice.getStatus().toString())
                .put("status", invoice.getStatus().toInt());

        return node;
    }
    /*
     ** Update operation for user invoice info
     */
    @RequestMapping("/invoice/update/{id}/status/{status}")
    public String updateInvoice(@PathVariable("id") long id, @PathVariable("status") int status){
        String ans = "failure";
        try{
            Invoice invoice = invoiceService.getById(id).setStatus(status);
            invoiceService.update(invoice);
            ans = "Success";
        }catch(Exception ex){
            logger.error("UPDATE_ORDER: " + ex);
        }
        return ans;
    }
}

