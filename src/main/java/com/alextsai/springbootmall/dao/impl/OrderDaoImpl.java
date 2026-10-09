package com.alextsai.springbootmall.dao.impl;

import com.alextsai.springbootmall.dao.OrderDao;
import com.alextsai.springbootmall.model.Order;
import com.alextsai.springbootmall.model.OrderItem;
import com.alextsai.springbootmall.model.User;
import com.alextsai.springbootmall.rowmapper.OrderItemRowMapper;
import com.alextsai.springbootmall.rowmapper.OrderRowMapper;
import com.alextsai.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Order getOrderById(Integer orderId) {
        String sql ="SELECT order_id,user_id,total_amount,created_date,last_modified_date from `order` where order_id = :orderId";
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        if(!orderList.isEmpty()){
            return orderList.get(0);
        }
        return null;
    }

    @Override
    public Integer createOrder(Integer userId, int totalAmount) {
        String sql = "INSERT INTO `order`(user_id,total_amount,created_date,last_modified_date) " +
                "VALUES (:user_id,:total_amount,:createdDate,:lastModifiedDate)";
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",userId);
        map.put("total_amount",totalAmount);

        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);

        KeyHolder keyHolder= new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void createOrderItem(Integer orderId, List<OrderItem> orderItemList) {
        //使用batchUpdate 一次性加入數據，效率較高。
        String sql = "INSERT INTO order_item (order_id,product_id,quantity,amount) " +
                "VALUES (:orderId,:productId,:quantity,:amount)";
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];

        for(int i = 0;i<orderItemList.size();i++){
            OrderItem orderItem = orderItemList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId",orderId);
            parameterSources[i].addValue("productId",orderItem.getProductId());
            parameterSources[i].addValue("quantity",orderItem.getQuantity());
            parameterSources[i].addValue("amount",orderItem.getAmount());

        }
        namedParameterJdbcTemplate.batchUpdate(sql,parameterSources);
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(Integer orderId) {
        String sql ="SELECT a.order_item_id,a.order_id,a.product_id,a.quantity,a.amount,b.product_name,b.image_url" +
                " from order_item a " +
                "left join product b on a.product_id = b.product_id" +
                " where a.order_id = :orderId";
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());

        return orderItemList;
    }
}
