package com.feliperodsdev.orderservice.repository;

import com.feliperodsdev.orderservice.model.OrderItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("OrderItemMySqlRepository")
public interface OrderItemMySqlRepository extends JpaRepository<OrderItem, Long> {
}
