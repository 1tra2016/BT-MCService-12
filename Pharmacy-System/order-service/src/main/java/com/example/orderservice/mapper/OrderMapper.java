package com.example.orderservice.mapper;

import com.example.orderservice.dto.order.OrderRequestDTO;
import com.example.orderservice.dto.order.OrderResponseDTO;
import com.example.orderservice.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderRequestDTO request);
    OrderResponseDTO toResponseDTO(Order order);
}