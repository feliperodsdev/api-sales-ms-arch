package com.feliperodsdev.productservice.repositories;

import com.feliperodsdev.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("ProductRepositoryImplMongodb")
public class ProductRepositoryImpl implements IProductRepository {

    @Autowired
    ProductMongoRepository repository;

    @Override
    public void save(Product product) {
        this.repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.repository.findAll();
    }
}
