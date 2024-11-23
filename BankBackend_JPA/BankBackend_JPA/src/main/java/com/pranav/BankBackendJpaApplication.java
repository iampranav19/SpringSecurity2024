package com.pranav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankBackendJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankBackendJpaApplication.class, args);
		System.out.println("++++++++++++ Bank Backend JPA Up and Running ++++++++++++");

	}

}
