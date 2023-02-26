package com.feliperodsdev.productservice.services;

import com.feliperodsdev.productservice.model.Product;
import com.feliperodsdev.productservice.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsService {

    private IProductRepository repository;

    public GetAllProductsService(@Qualifier("ProductRepositoryImplMongodb") IProductRepository iProductRepository){
        this.repository = iProductRepository;
    }

    public List<Product> getAllProducts(){
        return this.repository.getAllProducts();
    }

}
