package com.example.display.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.util.Optional;

@FeignClient(
        name = "product",
        url = "localhost:8066",
        fallbackFactory = ProductRemoteFallbackFactory.class)
public interface ProductRemoteFeignService {

    @GetMapping("/external-api/products")
    Optional<String> products();
}
