package com.alextsai.springbootmall.service.impl;

import com.alextsai.springbootmall.dao.UserDao;
import com.alextsai.springbootmall.dto.UserLoginRequest;
import com.alextsai.springbootmall.dto.UserRegisterRequest;
import com.alextsai.springbootmall.model.User;
import com.alextsai.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;



@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Override
    public Integer register(UserRegisterRequest userRequest) {
        User user = userDao.getUserByEmail(userRequest.getEmail());
        if(user!=null){
            log.warn("email: {} 已經被註冊",userRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //加密密碼
        String hashedPassword = DigestUtils.md5DigestAsHex(userRequest.getPassword().getBytes());
        userRequest.setPassword(hashedPassword);
        return userDao.createUser(userRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());
        if(user==null){
            log.warn("該email {} 尚未註冊",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
        if(user.getPassword().equals(hashedPassword)){
            return user;
        }else{
            log.warn("email {} 的密碼不正確",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
