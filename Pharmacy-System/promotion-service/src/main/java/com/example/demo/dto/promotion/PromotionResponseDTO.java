package com.example.demo.dto.promotion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionResponseDTO {
    private Integer id;
    private Integer productId;
    private Integer discount;
    private String code;
}
