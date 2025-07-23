package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.response.OrderResponse;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    OrderResponse getOrderById(Long id);
}
