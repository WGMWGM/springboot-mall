package com.alextsai.springbootmall.dao;

import com.alextsai.springbootmall.dto.UserLoginRequest;
import com.alextsai.springbootmall.dto.UserRegisterRequest;
import com.alextsai.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRequest);

    User getUserById(Integer userId);

    User getUserByEmail(UserRegisterRequest userRequest);

    User getUserByEmail(UserLoginRequest userLoginRequest);
}
