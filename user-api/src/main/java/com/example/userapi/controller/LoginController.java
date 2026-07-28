package com.example.userapi.controller;

import com.example.userapi.dto.LoginResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;   // ← 确认 import 了这行


import java.util.Map;

@RestController
@CrossOrigin(origins = "*")   // ← 加上这一行，允许所有来源访问
public class LoginController {

    @PostMapping("/api/login")
    public LoginResponse login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if ("admin".equals(username) && "123456".equals(password)) {
            return new LoginResponse(200, "登录成功", "fake-jwt-token");
        } else {
            return new LoginResponse(401, "用户名或密码错误");
        }
    }
}