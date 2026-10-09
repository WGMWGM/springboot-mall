package com.alextsai.springbootmall.service;

import com.alextsai.springbootmall.dto.CreateOrderRequest;
import com.alextsai.springbootmall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
