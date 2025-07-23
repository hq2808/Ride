package com.example.orderservice.service.impl;

import com.example.orderservice.client.UserClient;
import com.example.orderservice.dto.UserDto;
import com.example.orderservice.event.OrderPlacedEvent;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.response.OrderResponse;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;


    @Override
    public Order createOrder(Order order) {
        Order saved = orderRepository.save(order);

        // Gửi event Kafka
        OrderPlacedEvent event = OrderPlacedEvent.builder()
                .orderId(saved.getId())
                .userId(saved.getUserId())
                .total(saved.getTotal())
                .build();

        kafkaTemplate.send("order-topic", event);
        System.out.println("📤 Đã gửi Kafka event: " + event);

        return saved;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }

        // Gọi user-service bằng Feign
        UserDto user = userClient.getUserById(order.getUserId());

        return new OrderResponse(order.getId(), order.getProduct(), order.getPrice(), user);
    }
}
