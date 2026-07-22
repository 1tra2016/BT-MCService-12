package com.example.demo.controller;

import com.example.demo.dto.product.ProductCreateDTO;
import com.example.demo.dto.product.ProductResponseDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> create(
            @RequestBody ProductCreateDTO dto
    ){
        return ResponseEntity.ok(ApiResponse.success(
                200,
                service.create(dto)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                200,
                service.getById(id)
        ));
    }

}