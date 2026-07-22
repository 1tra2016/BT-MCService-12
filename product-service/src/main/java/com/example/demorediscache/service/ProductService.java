package com.example.demorediscache.service;

import com.example.demorediscache.dto.ProductUpdateDTO;
import com.example.demorediscache.entity.Product;
import com.example.demorediscache.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    @Cacheable(value = "products", key = "#id")
    public Product getById(Long id){
        System.out.println("Đang truy vấn Database");
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id: " + id));
    }

    @CacheEvict(value = "products", key = "#id")
    public Product update(Long id, ProductUpdateDTO dto){
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id: " + id));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return repository.save(product);
    }
}