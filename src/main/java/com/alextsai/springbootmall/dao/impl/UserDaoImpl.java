package com.alextsai.springbootmall.dao.impl;

import com.alextsai.springbootmall.dao.UserDao;
import com.alextsai.springbootmall.dto.UserLoginRequest;
import com.alextsai.springbootmall.dto.UserRegisterRequest;
import com.alextsai.springbootmall.model.User;
import com.alextsai.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Integer createUser(UserRegisterRequest userRequest) {
        String sql = "INSERT INTO user(email,password,created_date,last_modified_date) " +
                "VALUES (:email,:password,now(),now())";
        Map<String,Object> map = new HashMap<>();
        map.put("email",userRequest.getEmail());
        map.put("password",userRequest.getPassword());

        KeyHolder keyHolder= new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);

        int userId = keyHolder.getKey().intValue();

        return userId;
    }

    @Override
    public User getUserById(Integer userId) {
        String sql ="select user_id,email,password,created_date,last_modified_date from user where user_id = :user_id";
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",userId);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if(!userList.isEmpty()){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public User getUserByEmail(UserRegisterRequest userRequest) {
        String sql ="select user_id,email,password,created_date,last_modified_date from user where email = :email";
        Map<String,Object> map = new HashMap<>();
        map.put("email",userRequest.getEmail());
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if(!userList.isEmpty()){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public User getUserByEmail(UserLoginRequest userLoginRequest) {
        String sql ="select user_id,email,password,created_date,last_modified_date from user where email = :email";
        Map<String,Object> map = new HashMap<>();
        map.put("email",userLoginRequest.getEmail());
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if(!userList.isEmpty()){
            return userList.get(0);
        }
        return null;
    }
}
