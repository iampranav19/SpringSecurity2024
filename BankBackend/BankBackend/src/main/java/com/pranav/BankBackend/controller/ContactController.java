package com.pranav.BankBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @GetMapping
    public String getContactInfo(){
        int min = 10000000;
        int max = 99999999;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return "{\"phone\":\"" + randomNum + "\",\"email\":\"pranav@gmail.com\"}";
    }



}
