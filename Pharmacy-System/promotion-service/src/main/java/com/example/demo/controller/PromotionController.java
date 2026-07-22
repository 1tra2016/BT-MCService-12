package com.example.demo.controller;


import com.example.demo.dto.promotion.PromotionCreateDTO;
import com.example.demo.dto.promotion.PromotionResponseDTO;
import com.example.demo.dto.promotion.PromotionUpdateDTO;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService service;

    @PostMapping
    public ResponseEntity<ApiResponse<PromotionResponseDTO>> create(
            @RequestBody PromotionCreateDTO dto
    ){
        return ResponseEntity.ok(ApiResponse.success(
                200,
                service.createPromotion(dto)
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PromotionResponseDTO>> update(
            @PathVariable Integer id,
            @RequestBody PromotionUpdateDTO dto
    ) {
        service.updatePromotion(id, dto);
        return ResponseEntity.ok(ApiResponse.success(
                200,
                null
        ));
    }

}