package ru.otus.product.details.service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.product.details.service.dtos.ProductDetailsDto;

@RestController
@RequestMapping("/api/v1/details")
public class ProductDetailsController {
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsDto> getProductDetailsById(@PathVariable Long id) throws InterruptedException {
        if (id > 100) {
            throw new RuntimeException();
        }
        Thread.sleep(2500 + (int)(Math.random() * 2500));
        var responseBody = new ProductDetailsDto(id, String.format("Product id %s description..", id));
        var responseStatus = HttpStatus.OK;
        if (id % 2 == 0) {
            responseBody = null;
            responseStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(responseBody, responseStatus);
    }
}
