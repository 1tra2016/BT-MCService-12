package com.example.demo.mapper;

import com.example.demo.dto.product.ProductCreateDTO;
import com.example.demo.dto.product.ProductResponseDTO;
import com.example.demo.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO toResponseDTO(Product product);
    Product toEntity(ProductCreateDTO dto);
}