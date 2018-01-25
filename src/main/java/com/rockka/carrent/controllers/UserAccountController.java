package com.rockka.carrent.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rockka.carrent.domain.Order;
import com.rockka.carrent.domain.User;
import com.rockka.carrent.services.OrderService;
import com.rockka.carrent.services.UserService;
import com.rockka.carrent.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private OrderService orderService;
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

    @RequestMapping("/update" +
            "/firstname/{firstname}" +
            "/secondname/{secondname}" +
            "/lastname/{lastname}" +
            "/address/{address}" +
            "/about_me/{about_me}" +
            "/birthday/{birthday}")
    @ResponseBody
    public String updateUser(
            @PathVariable("firstname") String firstname
            , @PathVariable("secondname") String secondname
            , @PathVariable("lastname") String lastname
            , @PathVariable("address") String address
            , @PathVariable("about_me") String about_me
            , @PathVariable("birthday") String birthday
    ){
        String ans = "failure";
        User user = userService.getByUsername(getPrincipal().getUsername());
        if(user != null){
            userService.update(
                    user.setFirstName(firstname)
                            .setSecondName(secondname)
                            .setLastName(lastname)
                            .setAddress(address)
                            .setAboutMe(about_me)
                            .setBirthday(new Date(birthday))
            );
            ans = "success";
        }
        return ans;
    }

    @GetMapping("/order/show_all")
    @ResponseBody
    public JsonNode showOrders(){
        ArrayNode node = mapper.createArrayNode();
        List<Order> orders = orderService.getAllWithUser(getPrincipal().getUsername());
        for(Order order : orders){
            node.addObject()
                    .put("order_id", order.getId())
                    .put("car_name", order.getCar().getName())
                    .put("starts_at", order.getStartsAt().toString())
                    .put("expires_at", order.getExpiresAt().toString())
                    .put("order_price", order.getPrice())
                    .put("orderStatus", order.getOrderStatus().toString());
        }
        return node;
    }

    @GetMapping("/order/{id}")
    @ResponseBody
    public JsonNode showOrderById(@PathVariable("id") long id){
        ObjectNode node = mapper.createObjectNode();
        String username = getPrincipal().getUsername();
        Order order = orderService.getByIdWithUser(id, username);

        node.put("order_id", order.getId())
                .put("username", username)
                .put("car_name", order.getCar().getName())
                .put("price", order.getPrice())
                .put("starts_at", order.getStartsAt().toString())
                .put("expires_at", order.getExpiresAt().toString())
                .put("description", order.getDescription())
                .put("orderStatus", order.getOrderStatus().toString())
                .put("status", order.getOrderStatus().toInt());

        return node;
    }

    @RequestMapping("/order/update/{id}/status/{status}")
    @ResponseBody
    public String updateOrder(@PathVariable("id") long id, @PathVariable("status") int status){
        String ans = "failure";
        try{
            Order order = orderService.getById(id).setOrderStatus(status);
            orderService.update(order);
            ans = "Success";
        }catch(Exception ex){
            logger.error("UPDATE_ORDER: " + ex);
        }
        return ans;
    }



}

