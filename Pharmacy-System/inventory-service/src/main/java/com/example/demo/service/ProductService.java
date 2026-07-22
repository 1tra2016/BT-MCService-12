package com.example.demo.service;

import com.example.demo.dto.product.ProductCreateDTO;
import com.example.demo.dto.product.ProductResponseDTO;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final RedisTemplate<String, Integer> redisTemplate;

    public void decreaseStock(Integer productId, Integer quantity) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if(product.getStock() < quantity){
            throw new RuntimeException("Out of stock");
        }
        product.setStock(product.getStock() - quantity);
        Product saved = repository.save(product);

        redisTemplate.opsForValue().set(
                "stock:" + saved.getId(),
                saved.getStock()
        );
    }

    public ProductResponseDTO create(ProductCreateDTO dto) {

        Product product = mapper.toEntity(dto);

        Product saved = repository.save(product);

        redisTemplate.opsForValue().set(
                "stock:" + saved.getId(),
                saved.getStock()
        );

        return mapper.toResponseDTO(saved);
    }

    @Cacheable(value = "products", key = "#id")
    public ProductResponseDTO getById(Integer id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        System.out.println("read db");
        return mapper.toResponseDTO(product);
    }

    @PostConstruct
    public void loadStockToRedis() {
        repository.findAll().forEach(product ->
                redisTemplate.opsForValue().set(
                        "stock:" + product.getId(),
                        product.getStock()
                )
        );
    }

}