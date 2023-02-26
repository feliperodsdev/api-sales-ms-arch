package com.feliperodsdev.orderservice.repository;

import com.feliperodsdev.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMySqlRepository extends JpaRepository<Order, Long> {
}
