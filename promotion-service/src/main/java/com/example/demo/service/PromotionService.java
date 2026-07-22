package com.example.demo.service;

import com.example.demo.dto.promotion.PromotionCreateDTO;
import com.example.demo.dto.promotion.PromotionResponseDTO;
import com.example.demo.dto.promotion.PromotionUpdateDTO;
import com.example.demo.dto.promotion.PromotionUpdateEvent;
import com.example.demo.enity.Promotion;
import com.example.demo.publisher.PromotionPublisher;
import com.example.demo.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository repository;
    private final PromotionPublisher publisher;

    public void updatePromotion(Integer promotionId, PromotionUpdateDTO dto) {

        Promotion promotion = repository.findById(promotionId).orElseThrow( () -> new RuntimeException("Not found"));

        promotion.setDiscount(dto.getDiscount());
        promotion.setCode(dto.getCode());

        repository.save(promotion);

        publisher.publish(new PromotionUpdateEvent(promotion.getProductId(),promotion.getDiscount()));

    }

    public PromotionResponseDTO createPromotion(PromotionCreateDTO dto){
        Promotion promotion = new Promotion();
        promotion.setDiscount(dto.getDiscount());
        promotion.setCode(dto.getCode());
        promotion.setProductId(dto.getProductId());

        promotion = repository.save(promotion);

        PromotionResponseDTO responseDTO = new PromotionResponseDTO();
        responseDTO.setId(promotion.getId());
        responseDTO.setDiscount(dto.getDiscount());
        responseDTO.setCode(dto.getCode());
        responseDTO.setProductId(promotion.getProductId());

        publisher.publish(new PromotionUpdateEvent(promotion.getProductId(),promotion.getDiscount()));
        return responseDTO;
    }

}