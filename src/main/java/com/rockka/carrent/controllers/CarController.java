package com.rockka.carrent.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rockka.carrent.dao.CarDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {
    @Autowired
    private CarDao carService;
    @Autowired
    private ObjectMapper mapper;
    private Logger logger = LoggerFactory.getLogger(CarController.class);

    @GetMapping("/car/getall")
    public String getAll() {
        String line = "failure";
        try {
            line = mapper.writeValueAsString(carService.getAll());
        } catch (JsonProcessingException e) {
            logger.error("" + e);
        }
        return line;
    }

}
