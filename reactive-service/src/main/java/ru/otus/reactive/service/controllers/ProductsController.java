package ru.otus.reactive.service.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.reactive.service.dtos.ProductDto;
import ru.otus.reactive.service.entities.Product;
import ru.otus.reactive.service.services.ProductsService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;
    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    @GetMapping
    public Flux<Product> getAllProducts() {
        return productsService.findAll()
                .doOnError(error -> logger.error("Error while fetching all products", error));
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable("id") Long productId) {
        return productsService.findById(productId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")))
                .doOnError(error -> logger.error("Error while fetching product by id: " + productId, error));
    }

    @PostMapping
    public Mono<Void> createProduct(@RequestBody ProductDto productDto) {
        return productsService.create(productDto)
                .then()
                .doOnError(error -> logger.error("Error while creating product", error));
    }
}