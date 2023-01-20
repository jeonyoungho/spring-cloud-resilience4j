package com.example.display.controller;


import com.example.display.service.ProductDisplayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/display")
public class DisplayController {

    private final ProductDisplayService productDisplayService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/products")
    public String products() {
        return productDisplayService.displayProducts();
    }
}
