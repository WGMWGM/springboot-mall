package com.alextsai.springbootmall.controller;

import com.alextsai.springbootmall.dto.UserLoginRequest;
import com.alextsai.springbootmall.dto.UserRegisterRequest;
import com.alextsai.springbootmall.model.User;
import com.alextsai.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("user/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRequest) {
        Integer userId = userService.register(userRequest);
        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @PostMapping("user/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        User user = userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
