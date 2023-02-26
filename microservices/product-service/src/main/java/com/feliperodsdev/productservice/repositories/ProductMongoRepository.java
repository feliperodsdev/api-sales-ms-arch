package com.feliperodsdev.productservice.repositories;

import com.feliperodsdev.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoRepository extends MongoRepository<Product, String> {
}
