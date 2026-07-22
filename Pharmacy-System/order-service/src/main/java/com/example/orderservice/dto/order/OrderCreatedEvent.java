package com.example.orderservice.dto.order;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private String customerEmail;
}