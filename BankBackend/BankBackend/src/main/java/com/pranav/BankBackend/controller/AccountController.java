package com.pranav.BankBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @GetMapping("/getAccountNumber")
    public String getAccountNumber(){
        int min = 10000000;
        int max = 99999999;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return "Here is your account number: "+randomNum;
    }



}
