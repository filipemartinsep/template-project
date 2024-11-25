package com.exemplo.api_user.products.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @GetMapping("/api/hello")
    public String hello()
    { return "Hello, World from Spring Boot!"; }
}
