package com.example.demo.service;

import com.example.demo.dto.order.OrderCreatedEvent;
import com.example.demo.dto.order.OrderRequestDTO;
import com.example.demo.dto.order.OrderResponseDTO;
import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.producer.OrderProducer;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderProducer producer;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate redisTemplate;


    public OrderResponseDTO create(OrderRequestDTO dto) {
        RLock lock = redissonClient.getLock("lock:product:" + dto.getProductId());
        try {
            boolean locked = lock.tryLock(5, 10, TimeUnit.SECONDS);
            if (!locked) {
                throw new RuntimeException("Hệ thống đang bận");
            }

            // Đọc tồn kho từ Redis
            String value = redisTemplate.opsForValue()
                    .get("stock:" + dto.getProductId());
            if (value == null) {
                throw new RuntimeException("Không tìm thấy thông tin tồn kho");
            }
            int stock = Integer.parseInt(value);

            // Kiểm tra tồn kho
            if (stock < dto.getQuantity()) {
                throw new RuntimeException("Sản phẩm đã hết hàng");
            }

            // Trừ kho trên Redis
            stock -= dto.getQuantity();
            redisTemplate.opsForValue().set(
                    "stock:" + dto.getProductId(),
                    String.valueOf(stock)
            );

            // Lưu đơn hàng
            Order order = mapper.toEntity(dto);
            Order saved = repository.save(order);

            // Gửi Kafka
            producer.publish(new OrderCreatedEvent(
                    saved.getId(),
                    saved.getProductId(),
                    saved.getQuantity(),
                    saved.getCustomerEmail()
            ));
            return mapper.toResponseDTO(saved);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}