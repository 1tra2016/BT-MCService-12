package com.example.orderservice.dto.order;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Integer id;
    private Integer productId;
    private Integer quantity;
    private String customerEmail;
}