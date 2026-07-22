package com.example.demorediscache.controller;

import com.example.demorediscache.dto.ProductUpdateDTO;
import com.example.demorediscache.entity.Product;
import com.example.demorediscache.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productsService;

    @GetMapping("/{id}")
    public Product getProduct(
            @PathVariable Long id){

        return productsService.getById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateDTO dto
    ){
        return productsService.update(id, dto);
    }

}