package com.example.demo.service;

import com.example.demo.dto.product.ProductCreateDTO;
import com.example.demo.dto.product.ProductResponseDTO;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public void decreaseStock(Integer productId, Integer quantity) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if(product.getStock() < quantity){
            throw new RuntimeException("Out of stock");
        }
        product.setStock(product.getStock() - quantity);
        repository.save(product);
    }

    public ProductResponseDTO create(ProductCreateDTO dto) {
        Product product = mapper.toEntity(dto);
        Product saved = repository.save(product);
        return mapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO getById(Integer id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapper.toResponseDTO(product);
    }
}