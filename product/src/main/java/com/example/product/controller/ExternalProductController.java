package com.example.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/external-api")
public class ExternalProductController {

    @GetMapping("/products")
    public String products() {
        throw new RuntimeException("runtime exception!!!");
//        return "products exist!";
    }
}
