package com.alextsai.springbootmall.rowmapper;

import com.alextsai.springbootmall.constant.ProductCategory;
import com.alextsai.springbootmall.model.Order;
import com.alextsai.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();

        order.setOrderId(rs.getInt("order_id"));
        order.setUser_id(rs.getInt("user_id"));
        order.setTotal_amount(rs.getInt("total_amount"));
        order.setCreatedDate(rs.getTimestamp("created_date"));
        order.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return order;
    }
}
