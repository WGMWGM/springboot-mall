package com.alextsai.springbootmall.service;

import com.alextsai.springbootmall.dto.UserRequest;
import com.alextsai.springbootmall.model.User;

public interface UserService {
    Integer register(UserRequest userRequest);

    User getUserById(Integer userId);
}
