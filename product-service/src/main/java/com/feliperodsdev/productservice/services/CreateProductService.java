package com.feliperodsdev.productservice.services;

import com.feliperodsdev.productservice.dtos.CreateProductDto;
import com.feliperodsdev.productservice.model.Product;
import com.feliperodsdev.productservice.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService {

    private IProductRepository productRepository;

    public CreateProductService(@Qualifier("ProductRepositoryImplMongodb") IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(CreateProductDto createProductDto){
        Product product = new Product(null, createProductDto.getName(), createProductDto.getDesc(), createProductDto.getPrice());
        this.productRepository.save(product);
    }

}
