# Implementation Plan: 登录接口测试

**Branch**: `001-login-api-test` | **Date**: 2026-07-25 | **Spec**: specs/001-login-api-test/spec.md

**Input**: Feature specification from `specs/001-login-api-test/spec.md`

## Summary

为现有 `/api/login` 接口生成完整的控制器层测试套件，使用 Java 17 + Spring Boot Test + JUnit 5 + MockMvc，覆盖功能测试、异常测试、安全测试三大维度，测试覆盖率 ≥ 85%。

## Technical Context

**Language/Version**: Java 17

**Primary Dependencies**: Spring Boot Starter Test (JUnit 5 + MockMvc + Hamcrest)

**Storage**: N/A — 纯控制器层测试，无数据库依赖

**Testing**: JUnit 5 (Jupiter) + Spring MockMvc + JaCoCo 覆盖率

**Target Platform**: JVM 17 (Spring Boot 3.2.0)

**Project Type**: Web service (REST API) 测试套件

**Performance Goals**: MockMvc 单线程模拟，所有测试在 30 秒内完成

**Constraints**: 不依赖外部数据库，不启动真实 HTTP 服务器

**Scale/Scope**: 单个 Controller 方法 (LoginController.login)，约 17-20 个测试用例

## Constitution Check

GATE 状态: ⚠️ 模板内容，无实际约束 — 通过

## Project Structure

### Documentation (this feature)

```
specs/001-login-api-test/
├── plan.md              # 本文件 (/speckit-plan 输出)
├── research.md          # Phase 0 输出 — 技术决策记录
├── data-model.md        # Phase 1 输出 — 请求/响应数据模型
├── quickstart.md        # Phase 1 输出 — 快速验证指南
├── contracts/           # Phase 1 输出 — API 合约文档
│   └── api-contract.md
└── spec.md              # 原始规范
```

### Source Code (repository root)

```
src/
├── main/java/com/example/userapi/
│   ├── UserApiApplication.java
│   ├── controller/
│   │   └── LoginController.java
│   └── dto/
│       └── LoginResponse.java
└── test/java/com/example/userapi/
    ├── controller/
    │   └── LoginControllerTest.java    # 控制器测试（目标文件）
    └── dto/
        └── LoginResponseTest.java      # DTO 单元测试（已有）
```

**Structure Decision**: 单项目结构，测试与源码在标准 Maven 目录布局下，测试类路径与被测类路径镜像。

## Complexity Tracking

无 — 宪法检查无违规