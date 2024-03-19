package ru.otus.reactive.service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.reactive.service.dtos.ProductDetailsDto;
import ru.otus.reactive.service.services.ProductDetailsService;

@RestController
@RequestMapping("/api/v1/detailed")
@RequiredArgsConstructor
public class ProductsDetailsController {

    private final ProductDetailsService productDetailsService;

    @GetMapping("/demo")
    public Flux<ProductDetailsDto> getManySlowProducts() {
        return Flux.just(1L, 2L, 3L)
                .flatMap(productDetailsService::getProductDetailsById);
    }

    @GetMapping("/{id}")
    public Mono<ProductDetailsDto> getProductDetailsById(@PathVariable("id") Long id) {
        return productDetailsService.getProductDetailsById(id);
    }

    @PostMapping("/")
    public Flux<ProductDetailsDto> getProductDetailsById(@RequestBody Flux<Long> ids) {
        return productDetailsService.getProductDetailsByIds(ids);
    }

    @GetMapping("/all")
    public Flux<ProductDetailsDto> getAllProductDetails() {
        return productDetailsService.getAllProductDetails();
    }
}