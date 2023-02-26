package com.feliperodsdev.orderservice.dtos;

import java.util.List;

public class DoOrderDto {

    private List<OrderItemDto> orderItemDtoList;

    public DoOrderDto(){}

    public void setOrderItemDtoList(List<OrderItemDto> orderItemDtoList) {
        this.orderItemDtoList = orderItemDtoList;
    }

    public DoOrderDto(List<OrderItemDto> orderItemDtoList) {
        this.orderItemDtoList = orderItemDtoList;
    }

    public List<OrderItemDto> getOrderItemDtoList() {
        return orderItemDtoList;
    }

}
