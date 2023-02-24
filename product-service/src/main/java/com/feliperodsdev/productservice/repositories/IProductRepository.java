package com.feliperodsdev.productservice.repositories;

import com.feliperodsdev.productservice.model.Product;

import java.util.List;

public interface IProductRepository {

    void save(Product product);
    List<Product> getAllProducts();
}
