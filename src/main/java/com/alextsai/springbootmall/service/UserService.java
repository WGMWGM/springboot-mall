package com.alextsai.springbootmall.service;

import com.alextsai.springbootmall.dto.UserLoginRequest;
import com.alextsai.springbootmall.dto.UserRegisterRequest;
import com.alextsai.springbootmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);
}
