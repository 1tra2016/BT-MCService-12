package com.example.demo.subscriber;

import com.example.demo.dto.promotion.PromotionUpdateEvent;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionSubscriber {

    private final CacheManager cacheManager;
    private final ProductRepository repository;

    public void receiveMessage(PromotionUpdateEvent event) {
        System.out.println("Cập nhật Discount cho product");

        Product product = repository.findById(event.getProductId())
                .orElseThrow( ()-> new RuntimeException("Not found"));

        product.setDiscount(event.getDiscount());

        repository.save(product);

        cacheManager.getCache("products").evict(event.getProductId());
    }

}