package com.rockka.carrent.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockka.carrent.dao.CarDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/save?")
    public String save(HttpServletRequest request){
        String line = "";
        Enumeration <String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()) {
            String head = headers.nextElement();
            logger.info("header name: " + head + "; header value: " + request.getHeader(head));
            line += "header name: " + head + "; header value: " + request.getHeader(head) + "\n";
        }

        line += "\n";
        logger.info("request URI: " +request.getRequestURI());
        logger.info("request URI: " +request.getRequestURL());
        line += "request URI: " +request.getRequestURI() + "\n" + "request URI: " +request.getRequestURL() + "\n";

        Enumeration <String> attr = request.getAttributeNames();
        while(attr.hasMoreElements()){
            String att = attr.nextElement();
            logger.info("Attribute name: " + att + "; Attribute value: " + request.getAttribute(att));
            line += "Attribute name: " + att + "; Attribute value: " + request.getAttribute(att) + "\n";
        }
        line += "\n";

        Map<String,String[]> map = request.getParameterMap();
        if(map!=null) {
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                logger.info("param key: " + entry.getKey());
                line += "param key: " + entry.getKey() + "\n";
                for (String s : entry.getValue()) {
                    logger.info("   param value: " + s);
                    line += "   param value: " + s + "\n";
                }
            }
        }
        return line;
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
