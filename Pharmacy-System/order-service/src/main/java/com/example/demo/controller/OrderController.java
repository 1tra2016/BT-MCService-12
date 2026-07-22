package com.example.demo.controller;

import com.example.demo.dto.apiresponse.ApiResponse;
import com.example.demo.dto.order.OrderRequestDTO;
import com.example.demo.dto.order.OrderResponseDTO;
import com.example.demo.service.OrderService;
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
