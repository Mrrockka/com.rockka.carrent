package com.rockka.carrent.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.rockka.carrent.dao.CarDao;
import com.rockka.carrent.domain.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
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
    public String getAll() {
        String line = "failure";
        try {
            line = mapper.writeValueAsString(carDao.getAll());
        } catch (JsonProcessingException e) {
            logger.error("" + e);
        }
        return line;
    }

    @PostMapping("/save")
    public String save(@RequestBody Car car) {
        String answer = "failure";

        try {
            carDao.save(car);
            answer += "Success";
        } catch (Exception ex) {
            logger.error("Save exception " + ex);
        }
        return answer;
    }

    @PostMapping("/info")
    public String info(HttpServletRequest request, @RequestBody String line) {
        logger.info("Line : " + line);

        try{
            Car car = mapper.readValue(line, Car.class);
            logger.info("Car " + car.getMark());
        }catch (Exception ex){
            logger.error("Convert error " + ex);
        }
        try{
            logger.info("JsonNode to String " + mapper.readTree(line).toString() + " : " + mapper.readTree(line));
        }catch (Exception ex){
            logger.error("JsonNode error " + ex);
        }
//        logger.info("Car " + car.getMark());
        Enumeration<String> headers = request.getHeaderNames();
        logger.info("Headers\n");
        while (headers.hasMoreElements()) {
            String head = headers.nextElement();
            logger.info("header name: " + head + "; header value: " + request.getHeader(head));
        }

        logger.info("Requst context path : " + request.getContextPath());
        logger.info("Requst query string : " + request.getQueryString());
        logger.info("Requst path info : " + request.getPathInfo());
        logger.info("Requst content type : " + request.getContentType());
        HttpSession session = request.getSession(false);
        if (session != null) {
            logger.info("Session context :" + session.getServletContext());
            logger.info("Session servlet context context :" + session.getServletContext().getContext(request
                    .getRequestURI
                            ()));
            logger.info("Session to String: " + session.toString());
            logger.info("Session context to String :" + session.getSessionContext().toString());
        }
       /* if(request.isAsyncSupported()) {
            request.startAsync(request, response);
            if (request.isAsyncStarted()) {
                logger.info("Context : " + request.getAsyncContext());
            } else {
                logger.info("Assync not started");
            }
        }else{
            logger.info("Assync not supported");
        }*/
        logger.info("Requst servlet path : " + request.getServletPath());
        logger.info("request URI: " + request.getRequestURI());
        logger.info("request URI: " + request.getRequestURL());


    /*    Enumeration<String> attr = request.getAttributeNames();
        logger.info("Attributes");
        while(attr.hasMoreElements()){
            String att = attr.nextElement();
            logger.info("Attribute head: " + att + "||| Attribute value: " + request.getAttribute(att));
        }*/
       /* logger.info("Parts");
        try {
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                logger.info("Part content type " + part.getContentType() + "||| part toString :" + part.toString());
                Collection<String> partHeaders = part.getHeaderNames();
                for (String head : partHeaders) {
                    logger.info("Part head name : " + head + "||| PArt head value: " + part.getHeader(head));
                }
            }
        } catch (IOException e) {
            logger.error("Has no parts");
        } catch (ServletException e) {
            logger.error("Has no parts and servlet");
        }*/

        Map<String, String[]> map = request.getParameterMap();
        if (!map.isEmpty()) {
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
        while (param.hasMoreElements()) {
            String para = param.nextElement();
            logger.info("Parameter head" + para + "||| Parameter value: " + request.getParameter(para));
        }


        return "Success";
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
