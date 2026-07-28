package com.example.userapi.controller;

import com.example.userapi.dto.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // ============================================================
    //  功能测试
    // ============================================================
    @Nested
    @DisplayName("功能测试")
    class FunctionalTests {

        @Test
        @DisplayName("正确用户名密码应返回 200 及 token")
        void loginSuccess() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("username", "admin", "password", "123456"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(200)))
                    .andExpect(jsonPath("$.message", is("登录成功")))
                    .andExpect(jsonPath("$.token", is("fake-jwt-token")));
        }

        @Test
        @DisplayName("错误密码应返回 401")
        void loginWrongPassword() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("username", "admin", "password", "wrong"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)))
                    .andExpect(jsonPath("$.message", is("用户名或密码错误")))
                    .andExpect(jsonPath("$.token", nullValue()));
        }

        @Test
        @DisplayName("错误用户名应返回 401")
        void loginWrongUsername() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("username", "hacker", "password", "123456"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)))
                    .andExpect(jsonPath("$.message", is("用户名或密码错误")));
        }

        @Test
        @DisplayName("用户名密码都错误应返回 401")
        void loginBothWrong() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("username", "hacker", "password", "guess"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)))
                    .andExpect(jsonPath("$.message", is("用户名或密码错误")));
        }
    }

    // ============================================================
    //  异常 / 边界测试
    // ============================================================
    @Nested
    @DisplayName("异常 / 边界测试")
    class ExceptionTests {

        @Test
        @DisplayName("缺少 username 字段应返回 401")
        void loginMissingUsername() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("password", "123456"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)))
                    .andExpect(jsonPath("$.message", is("用户名或密码错误")));
        }

        @Test
        @DisplayName("缺少 password 字段应返回 401")
        void loginMissingPassword() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("username", "admin"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)))
                    .andExpect(jsonPath("$.message", is("用户名或密码错误")));
        }

        @Test
        @DisplayName("username 为空字符串应返回 401")
        void loginEmptyUsername() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("username", "", "password", "123456"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)))
                    .andExpect(jsonPath("$.message", is("用户名或密码错误")));
        }

        @Test
        @DisplayName("password 为空字符串应返回 401")
        void loginEmptyPassword() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("username", "admin", "password", ""))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)))
                    .andExpect(jsonPath("$.message", is("用户名或密码错误")));
        }

        @Test
        @DisplayName("空 JSON 请求体应返回 401")
        void loginEmptyBody() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)))
                    .andExpect(jsonPath("$.message", is("用户名或密码错误")));
        }
    }

    // ============================================================
    //  安全测试
    // ============================================================
    @Nested
    @DisplayName("安全测试")
    class SecurityTests {

        @Test
        @DisplayName("SQL 注入用户名不应绕过认证")
        void loginSqlInjectionUsername() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(
                                    Map.of("username", "' OR '1'='1", "password", "123456"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)));
        }

        @Test
        @DisplayName("SQL 注入密码不应绕过认证")
        void loginSqlInjectionPassword() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(
                                    Map.of("username", "admin", "password", "' OR '1'='1"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)));
        }

        @Test
        @DisplayName("XSS 注入不应导致认证异常")
        void loginXssInjection() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(
                                    Map.of("username", "<script>alert('xss')</script>", "password", "123456"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)));
        }

        @Test
        @DisplayName("超长用户名不应导致异常")
        void loginVeryLongUsername() throws Exception {
            String longUsername = "a".repeat(10000);
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(
                                    Map.of("username", longUsername, "password", "123456"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)));
        }

        @Test
        @DisplayName("特殊字符密码应正常工作")
        void loginSpecialCharacters() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(
                                    Map.of("username", "admin", "password", "!@#$%^&*()_+="))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)));
        }

        @Test
        @DisplayName("Unicode 用户名应正常工作")
        void loginUnicodeUsername() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(
                                    Map.of("username", "管理员", "password", "123456"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)));
        }

        @Test
        @DisplayName("null 字段值应返回 401")
        void loginNullFields() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"username\": null, \"password\": \"123456\"}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(401)));
        }

        @Test
        @DisplayName("多余字段不应影响认证结果")
        void loginExtraFields() throws Exception {
            mockMvc.perform(post("/api/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of(
                                    "username", "admin",
                                    "password", "123456",
                                    "extra", "ignored"))))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code", is(200)))
                    .andExpect(jsonPath("$.token", is("fake-jwt-token")));
        }
    }
}