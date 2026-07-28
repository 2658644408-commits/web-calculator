package com.example.userapi.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    @Test
    void testSuccessResponse() {
        LoginResponse resp = new LoginResponse(200, "登录成功", "fake-jwt-token");
        assertEquals(200, resp.getCode());
        assertEquals("登录成功", resp.getMessage());
        assertEquals("fake-jwt-token", resp.getToken());
    }

    @Test
    void testFailureResponse() {
        LoginResponse resp = new LoginResponse(401, "用户名或密码错误");
        assertEquals(401, resp.getCode());
        assertEquals("用户名或密码错误", resp.getMessage());
        assertNull(resp.getToken());
    }

    @Test
    void testDefaultConstructorAndSetters() {
        LoginResponse resp = new LoginResponse();
        resp.setCode(403);
        resp.setMessage("禁止访问");
        resp.setToken("some-token");
        assertEquals(403, resp.getCode());
        assertEquals("禁止访问", resp.getMessage());
        assertEquals("some-token", resp.getToken());
    }
}