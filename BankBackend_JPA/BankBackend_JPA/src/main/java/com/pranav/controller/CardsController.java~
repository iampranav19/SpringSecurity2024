package com.pranav.BankBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardsController {

    @GetMapping("/infos")
    public String getCardsInfos(@RequestParam(defaultValue = "1234 5678 9012 3456") String cardNumber,
                                @RequestParam(defaultValue = "Pranav") String cardHolderName,
                                @RequestParam(defaultValue = "12/25") String cardExpirationDate) {
        return "{\"cardNumber\":\"" + cardNumber + "\",\"cardHolderName\":\"" + cardHolderName + "\",\"cardExpirationDate\":\"" + cardExpirationDate + "\"}";
    }



}
