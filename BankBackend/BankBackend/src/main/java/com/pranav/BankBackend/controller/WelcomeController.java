package com.pranav.BankBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping
    public String welcome() {
        return "Welcome to Bank Backend with security";
    }

    @GetMapping("/sum")
    public int sum(@RequestParam(defaultValue = "1") int num1, @RequestParam(defaultValue = "2") int num2) {
        return num1 + num2;
    }


    @GetMapping("/sub")
    public int sub(@RequestParam(defaultValue = "1") int num1, @RequestParam(defaultValue = "2") int num2) {
        return num1 - num2;
    }



}
