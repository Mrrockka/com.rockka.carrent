package com.rockka.carrent.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private final File items = new File("/carrent/resources/static/items/");
    private BufferedReader reader;
    @GetMapping("/header")
    public String header(){
        String line = "";

        try {
            File file = new File(items, "header.html");
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
        }catch(Exception ex){
            logger.error("File exception " + ex);
        } finally {
            try {
                reader.close();
            }catch (IOException e) {
               logger.error("Close exception " + e);
            }
        }
        return line;
    }

    @GetMapping("/footer")
    public String footer(){
        String line = "";
        try{
            File file = new File(items, "footer.html");
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
        }catch(Exception ex){
            logger.error("File exception " + ex);
        }finally {
            try {
                reader.close();
            }catch (IOException e) {
                logger.error("Close exception " + e);
            }
        }
        return line;
    }
    @GetMapping("/menu")
    public String menu(){
        String line = "";
        try{
            File file = new File(items, "menu.html");
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
        }catch(Exception ex){
            logger.error("File exception " + ex);
        }finally {
            try {
                reader.close();
            }catch (IOException e) {
                logger.error("Close exception " + e);
            }
        }
        return line;
    }
}
