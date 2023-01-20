package com.example.display.service;

import com.example.display.remote.ProductRemoteFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductDisplayService {

    private final ProductRemoteFeignService productRemoteFeignService;

    public String displayProducts() {
        Optional<String> products = productRemoteFeignService.products();
        if (!products.isPresent()) {
            log.error("product api result is empty!!");
            throw new RuntimeException("products is empty!");
        }

        return products.get();
    }
}
