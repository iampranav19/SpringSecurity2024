package com.pranav.controller;

import com.pranav.entity.Customer;
import com.pranav.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

     @PostMapping("/register")
    public ResponseEntity<String>  registerUser(@RequestBody Customer customer) {
        try {
            String hashedPassword = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashedPassword);
            Customer savedCustomer=customerRepository.save(customer);
            if(savedCustomer.getId()>0) {
                return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>("Error ! While Registering the user", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // complete the exception handling
            return new ResponseEntity<>("Error ! While Registering the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
