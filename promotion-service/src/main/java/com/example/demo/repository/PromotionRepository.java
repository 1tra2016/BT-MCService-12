package com.example.demo.repository;

import com.example.demo.enity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,Integer> {
    Promotion findByProductId(Integer productId);
}
