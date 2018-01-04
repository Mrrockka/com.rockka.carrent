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
        car.setModifiedAt(new Date()).setCreatedAt(new Date());
        try {
            carDao.save(car);
            answer += "Success";
        } catch (Exception ex) {
            logger.error("Save exception " + ex);
        }
        return answer;
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
