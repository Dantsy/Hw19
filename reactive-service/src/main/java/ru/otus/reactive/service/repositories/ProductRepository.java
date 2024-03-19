package ru.otus.reactive.service.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.reactive.service.entities.Product;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}
