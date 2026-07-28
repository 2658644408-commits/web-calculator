# API 合约: 登录接口

## 端点

**POST** `/api/login`

**Content-Type**: `application/json`

## 请求

```json
{
  "username": "string",
  "password": "string"
}
```

### 字段说明
- `username` (必填): 登录用户名
- `password` (必填): 登录密码

## 响应

### 成功 (200)

```json
{
  "code": 200,
  "message": "登录成功",
  "token": "fake-jwt-token"
}
```

### 认证失败 (401)

```json
{
  "code": 401,
  "message": "用户名或密码错误",
  "token": null
}
```

## 测试合约

测试验证以下合约约束:

1. 输入字段映射: body 中的 `username` 映射到 `LoginRequest.username`，`password` 映射到 `LoginRequest.password`
2. 缺失字段处理: body 中缺少字段时，映射值为 `null`，视为认证失败
3. 认证逻辑: 仅当 `username == "admin"` 且 `password == "123456"` 时认证通过
4. 失败安全性: 所有失败场景返回统一错误信息，不区分"用户不存在"和"密码错误"