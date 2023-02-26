package com.feliperodsdev.orderservice.service;

import com.feliperodsdev.orderservice.dtos.DoOrderDto;
import com.feliperodsdev.orderservice.dtos.OrderItemDto;
import com.feliperodsdev.orderservice.model.Order;
import com.feliperodsdev.orderservice.model.OrderItem;
import com.feliperodsdev.orderservice.repository.IOrderRepository;
import com.feliperodsdev.orderservice.repository.OrderItemMySqlRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoOrderService {

    private final IOrderRepository orderRepository;
    private final OrderItemMySqlRepository orderItemRepository;

    public DoOrderService(@Qualifier("OrderRepositoryMySql") IOrderRepository orderRepository, @Qualifier("OrderItemMySqlRepository") OrderItemMySqlRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void doOrder(DoOrderDto doOrderDto){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItem> orderItemList = doOrderDto.getOrderItemDtoList()
                .stream()
                .map(this::saveOrderItem)
                .collect(Collectors.toList());

        order.setOrderItemList(orderItemList);

        orderRepository.save(order);

    }

    private OrderItem saveOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();

        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setSkuCode(orderItemDto.getSkuCode());

        orderItemRepository.save(orderItem);

        return orderItem;

    }

}
