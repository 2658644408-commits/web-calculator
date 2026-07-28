# 快速验证指南: 登录接口测试

## 前置条件

- Java 17+
- Maven 3.8+
- 项目已克隆至本地

## 构建与测试

```bash
# 编译项目
mvn clean compile

# 运行全部测试 + 覆盖率报告
mvn clean verify
```

## 验证场景

### 1. 功能性验证

所有测试用例定义在 `LoginControllerTest` 中，使用 MockMvc 模拟 HTTP 请求。

| 嵌套测试类 | 测试数量 | 覆盖场景 |
|------------|----------|----------|
| FunctionalTests | 4 | 正确凭据、错误密码、错误用户名、全部错误 |
| ExceptionTests | 5 | 缺失字段、空字符串、空请求体 |
| SecurityTests | 8 | SQL注入、XSS、超长输入、特殊字符、Unicode、null字段、多余字段 |

### 2. 覆盖率验证

JaCoCo 配置在 `verify` 阶段自动执行:

```bash
mvn verify  # 执行测试 + 覆盖率检查（行覆盖率 ≥ 85%）
```

覆盖率报告位于: `target/site/jacoco/index.html`

### 3. 测试结果预期

- 全部 17+ 个测试用例通过
- JaCoCo 行覆盖率 ≥ 85%
- 无编译错误
- `mvn clean verify` 返回 BUILD SUCCESS

## 参考

- 测试合约: `contracts/api-contract.md`
- 数据模型: `data-model.md`
- 完整规范: `spec.md`