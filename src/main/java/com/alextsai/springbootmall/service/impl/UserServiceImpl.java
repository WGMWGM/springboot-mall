package com.alextsai.springbootmall.service.impl;

import com.alextsai.springbootmall.dao.UserDao;
import com.alextsai.springbootmall.dto.UserRequest;
import com.alextsai.springbootmall.model.User;
import com.alextsai.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Override
    public Integer register(UserRequest userRequest) {
        User user = userDao.getUserByEmail(userRequest);
        if(user!=null){
            log.warn("email: {} 已經被註冊",userRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return userDao.createUser(userRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
