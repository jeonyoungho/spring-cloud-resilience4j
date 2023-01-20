package com.example.display.remote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ProductRemoteFallbackFactory implements FallbackFactory<ProductRemoteFeignService> {
    @Override
    public ProductRemoteFeignService create(Throwable cause) {
        return new ProductRemoteFeignService() {

            @Override
            public Optional<String> products() {
                log.error("[ProductRemoteFallbackFactory] products API call error. " +
                        "cause = {}", cause);
                return Optional.empty();
            }
        };
    }
}
