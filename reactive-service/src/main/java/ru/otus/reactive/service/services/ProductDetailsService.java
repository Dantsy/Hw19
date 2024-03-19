package ru.otus.reactive.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.reactive.service.dtos.ProductDetailsDto;
import ru.otus.reactive.service.exceptions.AppException;
import ru.otus.reactive.service.integrations.ProductDetailsServiceIntegration;

@Service
@RequiredArgsConstructor
public class ProductDetailsService {

    private final ProductDetailsServiceIntegration productDetailsServiceIntegration;
    private final ProductsService productsService;

    public Mono<ProductDetailsDto> getProductDetailsById(Long id) {
        return productsService.findById(id)
                .switchIfEmpty(Mono.error(new AppException("PRODUCT_NOT_FOUND", HttpStatus.NOT_FOUND)))
                .flatMap(product -> productDetailsServiceIntegration.getProductDetailsById(product.getId())
                        .map(productDetailsDto -> new ProductDetailsDto(product.getId(), product.getName(),
                                productDetailsDto.getDescription())));
    }

    public Flux<ProductDetailsDto> getProductDetailsByIds(Flux<Long> ids) {
        return ids.flatMap(this::getProductDetailsById);
    }

    public Flux<ProductDetailsDto> getAllProductDetails() {
        return productsService.findAll()
                .flatMap(product -> getProductDetailsById(product.getId()));
    }
}