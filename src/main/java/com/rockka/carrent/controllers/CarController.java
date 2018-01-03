package com.rockka.carrent.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.domain.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarDao carDao;
    @Autowired
    private ObjectMapper mapper;
    private Logger logger = LoggerFactory.getLogger(CarController.class);

    @GetMapping("/getall")
    public String getAll(){
        String line = "failure";
        try{
            line =mapper.writeValueAsString(carDao.getAll());
        } catch (JsonProcessingException e) {
            logger.error("" + e);
        }
        return line;
    }

    @PostMapping("/save/{mark}/{model}/{price}/{year}")
    public String save(@PathVariable("{mark}") String mark, @PathVariable("{model}") String model, @PathVariable("{price}") double price, @PathVariable("{year}") Date year){
        String answer = "failure";

        Car car = new Car();
        car.setMark(mark).setModel(model).setPrice(price).setReleaseDate(year).setCreatedAt(new Date()).setModifiedAt(new Date()).setIsDeleted('n').setIsFree('y');

        try{
            carDao.save(car);
            answer += "Success";
        }catch(Exception ex){
            logger.error("Save exception " + ex);
        }
        return answer;
    }

    @PostMapping("/info")
    public String info(HttpServletRequest request){
        Enumeration<String> headers = request.getHeaderNames();
        logger.info("Headers\n");
        while(headers.hasMoreElements()) {
            String head = headers.nextElement();
            logger.info("header name: " + head + "; header value: " + request.getHeader(head));
        }

        logger.info("request URI: " +request.getRequestURI());
        logger.info("request URI: " +request.getRequestURL());

    /*    Enumeration<String> attr = request.getAttributeNames();
        logger.info("Attributes");
        while(attr.hasMoreElements()){
            String att = attr.nextElement();
            logger.info("Attribute head: " + att + "||| Attribute value: " + request.getAttribute(att));
        }*/

        Map<String,String[]> map = request.getParameterMap();
        if(!map.isEmpty()) {
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                logger.info("param key: " + entry.getKey());
                for (String s : entry.getValue()) {
                    logger.info("   param value: " + s);
                }
            }
        } else {
            logger.info("Has no map parameters\n");
        }

        Enumeration<String> param = request.getParameterNames();
        logger.info("Parameters");
        while(param.hasMoreElements()){
            String para = param.nextElement();
            logger.info("Parameter head" + para + "||| Parameter value: " + request.getParameter(para));
        }

        return "index";
    }

    public CarDao getCarDao() {
        return carDao;
    }

    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}
