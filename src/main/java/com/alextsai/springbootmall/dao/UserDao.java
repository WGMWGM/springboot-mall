package com.alextsai.springbootmall.dao;

import com.alextsai.springbootmall.dto.UserRequest;
import com.alextsai.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRequest userRequest);

    User getUserById(Integer userId);
}
