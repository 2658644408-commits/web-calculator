package com.example.userapi.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/calc")
public class CalculatorController {

    @PostMapping("/log")
    public Map<String, String> logResult(@RequestBody Map<String, String> body) {
        String expression = body.get("expression");
        String result = body.get("result");

        // 模拟把计算结果存到数据库
        System.out.println("收到计算记录: " + expression + " = " + result);

        return Map.of(
                "code", "200",
                "message", "记录成功",
                "saved", "true"
        );
    }
}