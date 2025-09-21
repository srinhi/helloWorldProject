package com.hackathon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractController {

    @GetMapping("/")
    public String home() {
        return "Contract Simplifier Application is running!";
    }

    // Add your other endpoints here
}
