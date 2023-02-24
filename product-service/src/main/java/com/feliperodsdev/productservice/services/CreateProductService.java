package com.feliperodsdev.productservice.services;

import com.feliperodsdev.productservice.dtos.CreateProductDto;
import com.feliperodsdev.productservice.model.Product;
import com.feliperodsdev.productservice.repositories.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService {

    private final IProductRepository productRepository;

    public CreateProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(CreateProductDto createProductDto){
        Product product = new Product(null, createProductDto.getName(), createProductDto.getDesc(), createProductDto.getPrice());
        this.productRepository.save(product);
    }

}
