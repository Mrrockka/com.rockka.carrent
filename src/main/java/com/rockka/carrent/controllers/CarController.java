package com.rockka.carrent.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockka.carrent.dao.CarDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
