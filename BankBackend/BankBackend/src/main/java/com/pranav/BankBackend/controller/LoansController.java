package com.pranav.BankBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/loans")
public class LoansController {

    @GetMapping("/getLoanDetails")
    public String getLoanDetails() {
        double min = 1000;
        double max = 99999;
        double randomNum = ThreadLocalRandom.current().nextDouble(min, max + 1);
        return "{\"amount\":\"" + randomNum + "\",\"interest\":\"12%\",\"duration\":\"1 Year\"}";
    }



}
