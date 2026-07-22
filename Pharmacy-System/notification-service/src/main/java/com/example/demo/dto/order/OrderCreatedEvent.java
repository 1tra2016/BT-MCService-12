package com.example.demo.dto.order;

import lombok.*;

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