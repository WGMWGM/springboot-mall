package com.alextsai.springbootmall.dao;

import com.alextsai.springbootmall.dto.OrderQueryParams;
import com.alextsai.springbootmall.model.Order;
import com.alextsai.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Order getOrderById(Integer orderId) ;

    Integer createOrder(Integer userId, int totalAmount);

    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);

    List<OrderItem> getOrderItemByOrderId(Integer orderId);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrders(OrderQueryParams orderQueryParams);
}
