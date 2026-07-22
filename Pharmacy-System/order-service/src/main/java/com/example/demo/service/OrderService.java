package com.example.demo.service;

import com.example.demo.dto.order.OrderCreatedEvent;
import com.example.demo.dto.order.OrderRequestDTO;
import com.example.demo.dto.order.OrderResponseDTO;
import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.producer.OrderProducer;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderProducer producer;
    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderResponseDTO create(OrderRequestDTO dto){

        Order order = mapper.toEntity(dto);

        Order saved = repository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                saved.getId(),
                saved.getProductId(),
                saved.getQuantity(),
                saved.getCustomerEmail()
        );

        producer.publish(event);

        return mapper.toResponseDTO(saved);
    }
}