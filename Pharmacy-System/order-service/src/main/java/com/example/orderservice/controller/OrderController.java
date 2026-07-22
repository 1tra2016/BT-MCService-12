package com.example.orderservice.controller;

import com.example.orderservice.dto.apiresponse.ApiResponse;
import com.example.orderservice.dto.order.OrderRequestDTO;
import com.example.orderservice.dto.order.OrderResponseDTO;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDTO>> create(
            @RequestBody OrderRequestDTO request
    ){
        return ResponseEntity.ok(ApiResponse.success(
                200,
                orderService.create(request)
        ));
    }

}
