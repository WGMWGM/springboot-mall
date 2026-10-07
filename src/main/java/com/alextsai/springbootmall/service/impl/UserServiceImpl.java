package com.alextsai.springbootmall.service.impl;

import com.alextsai.springbootmall.dao.UserDao;
import com.alextsai.springbootmall.dto.UserRequest;
import com.alextsai.springbootmall.model.User;
import com.alextsai.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public Integer createUser(UserRequest userRequest) {
        return userDao.createUser(userRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
