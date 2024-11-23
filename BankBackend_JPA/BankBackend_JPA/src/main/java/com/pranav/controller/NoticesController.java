package com.pranav.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/notices")
public class NoticesController {

    @GetMapping
    public String[] getNotices() {
        String[] notices = {"No service charge on your account", "Your account is now eligible for a loan", "Your new card is ready for pickup", "Your check has been processed", "Your account has been compromised, please contact us"};
        int randomNum = ThreadLocalRandom.current().nextInt(0, notices.length);
        return new String[]{notices[randomNum]};
    }



}
