package ru.otus.reactive.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.reactive.service.dtos.ProductDto;
import ru.otus.reactive.service.entities.Product;
import ru.otus.reactive.service.repositories.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductRepository productRepository;

    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    public Mono<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Mono<Product> create(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        return productRepository.save(product);
    }
}