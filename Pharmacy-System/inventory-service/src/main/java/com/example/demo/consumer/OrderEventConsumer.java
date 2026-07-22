package com.example.demo.consumer;

import com.example.demo.dto.order.OrderCreatedEvent;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumer {

    private final ProductService productService;

    @KafkaListener(
            topics = "order-events",
            groupId = "inventory-group"
    )
    public void consume(OrderCreatedEvent event){

        log.info("Receive event: {}", event);

        productService.decreaseStock(
                event.getProductId(),
                event.getQuantity()
        );
    }

}