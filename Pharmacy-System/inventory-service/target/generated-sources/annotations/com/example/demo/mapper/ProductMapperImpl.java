package com.example.demo.mapper;

import com.example.demo.dto.product.ProductCreateDTO;
import com.example.demo.dto.product.ProductResponseDTO;
import com.example.demo.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-22T16:00:39+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponseDTO toResponseDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDTO.ProductResponseDTOBuilder productResponseDTO = ProductResponseDTO.builder();

        productResponseDTO.id( product.getId() );
        productResponseDTO.name( product.getName() );
        productResponseDTO.stock( product.getStock() );

        return productResponseDTO.build();
    }

    @Override
    public Product toEntity(ProductCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( dto.getName() );
        product.stock( dto.getStock() );

        return product.build();
    }
}
