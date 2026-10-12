package com.alextsai.springbootmall.service;

import com.alextsai.springbootmall.dto.CreateOrderRequest;
import com.alextsai.springbootmall.dto.OrderQueryParams;
import com.alextsai.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrders(OrderQueryParams orderQueryParams);
}
