package com.pranav.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @GetMapping
    public double getBalance(){
        double min = 10000;
        double max = 999999;
        double randomNum = ThreadLocalRandom.current().nextDouble(min, max + 1);
        return randomNum;
    }




}
