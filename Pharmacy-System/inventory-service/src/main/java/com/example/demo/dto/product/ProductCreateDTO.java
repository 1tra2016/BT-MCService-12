package com.example.demo.dto.product;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {
    private String name;
    private Integer stock;
    private Double price;
    private Integer discount =0;
}
