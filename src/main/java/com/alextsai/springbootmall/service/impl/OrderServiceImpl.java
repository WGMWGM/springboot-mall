package com.alextsai.springbootmall.service.impl;

import com.alextsai.springbootmall.dao.OrderDao;
import com.alextsai.springbootmall.dao.ProductDao;
import com.alextsai.springbootmall.dto.BuyItem;
import com.alextsai.springbootmall.dto.CreateOrderRequest;
import com.alextsai.springbootmall.model.OrderItem;
import com.alextsai.springbootmall.model.Product;
import com.alextsai.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;

            //轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getProduct_id());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }
        //創建訂單
        Integer orderId = orderDao.createOrder(userId,totalAmount);
        //創建訂單明細
        orderDao.createOrderItem(orderId, orderItemList);
        return orderId;
    }
}
