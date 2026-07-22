package com.example.orderservice.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderRequestDTO {
    private Integer productId;
    private Integer quantity;
    private String customerEmail;
}
