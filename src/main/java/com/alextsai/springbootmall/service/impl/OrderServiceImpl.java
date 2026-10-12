package com.alextsai.springbootmall.service.impl;

import com.alextsai.springbootmall.dao.OrderDao;
import com.alextsai.springbootmall.dao.ProductDao;
import com.alextsai.springbootmall.dao.UserDao;
import com.alextsai.springbootmall.dto.BuyItem;
import com.alextsai.springbootmall.dto.CreateOrderRequest;
import com.alextsai.springbootmall.dto.OrderQueryParams;
import com.alextsai.springbootmall.model.Order;
import com.alextsai.springbootmall.model.OrderItem;
import com.alextsai.springbootmall.model.Product;
import com.alextsai.springbootmall.model.User;
import com.alextsai.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        User user = userDao.getUserById(userId);
        if(user==null){
            log.warn("該userId {} 不存在",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());
            //檢查商品存在及庫存量是否足夠
            if (product==null){
                log.warn("該productId {} 不存在",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if(product.getStock()<buyItem.getQuantity()){
                log.warn("該productId {} 庫存不足，無法購買。剩餘 {} ，需要 {}",buyItem.getProductId(),product.getStock(),buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //扣除商品庫存
            productDao.updateStock(product.getProduct_id(),product.getStock()-buyItem.getQuantity());

            //計算總價格
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

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);
        order.setOrderItemList(orderItemList);
        return order;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);
        for (Order order:orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(order.getOrderId());
            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    @Override
    public Integer countOrders(OrderQueryParams orderQueryParams) {
        return orderDao.countOrders(orderQueryParams);
    }
}
