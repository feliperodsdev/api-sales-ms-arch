package com.feliperodsdev.orderservice.repository;

import com.feliperodsdev.orderservice.model.Order;

public interface IOrderRepository {

    void save(Order order);

}
