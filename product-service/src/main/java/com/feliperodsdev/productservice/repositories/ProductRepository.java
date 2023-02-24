package com.feliperodsdev.productservice.repositories;

import com.feliperodsdev.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
