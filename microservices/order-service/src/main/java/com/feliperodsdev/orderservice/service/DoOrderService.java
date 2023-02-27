package com.feliperodsdev.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feliperodsdev.orderservice.dtos.*;
import com.feliperodsdev.orderservice.model.Order;
import com.feliperodsdev.orderservice.model.OrderItem;
import com.feliperodsdev.orderservice.repository.IOrderRepository;
import com.feliperodsdev.orderservice.repository.OrderItemMySqlRepository;
import com.feliperodsdev.orderservice.service.exceptions.InvalidStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Service
public class DoOrderService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private final WebClient webClient;
    private final IOrderRepository orderRepository;
    private final OrderItemMySqlRepository orderItemRepository;

    public DoOrderService(WebClient webClient, @Qualifier("OrderRepositoryMySql") IOrderRepository orderRepository, @Qualifier("OrderItemMySqlRepository") OrderItemMySqlRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.webClient = webClient;
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

        List<String> skuCodes = order.getOrderItemList().stream()
                .map(OrderItem::getSkuCode)
                .collect(Collectors.toList());

        ResponseObject response = webClient.get()
                .uri("http://localhost:8083/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                        .retrieve()
                                .bodyToMono(ResponseObject.class)
                                        .block();

        if(response.getStatusCode() == 200){
            List<InventoryResponse> inventoryResponses = ((List<Object>) response.getData())
                    .stream()
                    .map(obj -> objectMapper.convertValue(obj, InventoryResponse.class))
                    .collect(Collectors.toList());

            if(!verifyStock(inventoryResponses)){
                throw new InvalidStockException();
            }

            orderItemRepository.saveAll(order.getOrderItemList());
            orderRepository.save(order);
        } else throw new IllegalArgumentException();

    }

    private boolean verifyStock(List<InventoryResponse> inventoryResponses) {
        int size = inventoryResponses.size();
        for(int i = 0; i<size; ++i) {
            if(inventoryResponses.get(i).isInStock() == false) return false;
        }
        return true;
    }

    private OrderItem saveOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();

        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setSkuCode(orderItemDto.getSkuCode());

        return orderItem;
    }

}
