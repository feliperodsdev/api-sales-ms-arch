package com.feliperodsdev.orderservice.repository;

import com.feliperodsdev.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("OrderRepositoryMySql")
public class OrderRepositoryImpl implements IOrderRepository {

    @Autowired
    OrderMySqlRepository repository;

    @Override
    public void save(Order order) {
        this.repository.save(order);
    }

}
