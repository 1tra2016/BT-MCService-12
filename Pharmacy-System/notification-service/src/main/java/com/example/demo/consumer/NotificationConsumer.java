package com.example.demo.consumer;

import com.example.demo.dto.order.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    @KafkaListener(
            topics = "order-events",
            groupId = "notification-group"
    )
    public void listen(OrderCreatedEvent event) {
        System.out.println("Hóa đơn cho đơn hàng "+event.getCustomerEmail()+" đã được gửi tới khách hàng");

    }

}