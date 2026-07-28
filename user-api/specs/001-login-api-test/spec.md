# Feature Specification: 登录接口测试

**Feature Branch**: `001-login-api-test`

**Created**: 2026-07-25

**Status**: Draft

**Input**: User description: "测试登录接口 /api/login。测试场景包括：正确的账号密码(admin/123456)返回成功；错误密码返回401；空用户名或空密码返回400；SQL注入字符(如 ' OR '1'='1)不能绕过登录；高频请求(10次/秒)不导致服务崩溃。"

## User Scenarios & Testing

### User Story 1 - 正常登录认证 (Priority: P1)

作为系统用户，当我使用正确的账号密码登录时，系统应验证我的身份并返回成功结果。

**Why this priority**: 这是登录接口最核心的功能路径，所有其他测试依赖于明确正确的认证行为作为基准。

**Independent Test**: 发送包含正确用户名 admin 和正确密码 123456 的 POST 请求到 /api/login，可以独立验证登录成功逻辑是否正确。

**Acceptance Scenarios**:

1. **Given** 登录接口 /api/login 可用，**When** 发送 POST 请求且 body 包含 `{"username":"admin","password":"123456"}`，**Then** 响应 HTTP 状态码为 200，返回 JSON 包含 `code: 200`、`message: "登录成功"`、`token: "fake-jwt-token"`
2. **Given** 登录接口可用，**When** 使用正确用户名 admin 和正确密码 123456 调用，**Then** 响应体中的 token 字段不为 null

---

### User Story 2 - 错误密码拒绝 (Priority: P1)

作为系统用户，当我使用正确的用户名但错误的密码登录时，系统应拒绝认证并返回明确的错误提示。

**Why this priority**: 密码错误是登录场景中最常见的失败路径，必须明确拒绝以保护账户安全。

**Independent Test**: 发送包含正确用户名 admin 和任意错误密码的 POST 请求，独立验证认证拒绝逻辑。

**Acceptance Scenarios**:

1. **Given** 登录接口 /api/login 可用，**When** 发送 POST 请求且 body 包含 `{"username":"admin","password":"wrong"}`，**Then** 返回 JSON 包含 `code: 401`、`message: "用户名或密码错误"`、token 为 null
2. **Given** 登录接口可用，**When** 使用各种错误密码（空字符串、特殊字符、超长字符串）调用，**Then** 始终返回 `code: 401`

---

### User Story 3 - 空参数校验 (Priority: P1)

作为API调用方，当我提交空用户名或空密码时，系统应明确拒绝并返回 400 状态码，指明参数校验失败。

**Why this priority**: 空参数是接口健壮性的基本要求，必须在逻辑处理前进行校验，防止无效请求进入业务层。

**Independent Test**: 分别提交缺少 username 字段、缺少 password 字段、或字段值为空字符串的请求，独立验证参数校验逻辑。

**Acceptance Scenarios**:

1. **Given** 登录接口 /api/login 可用，**When** 发送 POST 请求且 body 为 `{"username":"","password":"123456"}`，**Then** 响应 HTTP 状态码为 400，返回 JSON 包含 `code: 400`、`message` 指明用户名不能为空
2. **Given** 登录接口可用，**When** 发送 POST 请求且 body 为 `{"username":"admin","password":""}`，**Then** 响应 HTTP 状态码为 400，返回 JSON 包含 `code: 400`、`message` 指明密码不能为空
3. **Given** 登录接口可用，**When** 发送 POST 请求且 body 为 `{}`，**Then** 响应 HTTP 状态码为 400
4. **Given** 登录接口可用，**When** 发送 POST 请求且 body 缺少 username 字段，**Then** 响应 HTTP 状态码为 400

---

### User Story 4 - SQL注入防护 (Priority: P2)

作为恶意攻击者，当我尝试在用户名或密码字段中注入 SQL 语句时，系统应严格将其视为普通字符串处理，拒绝认证而非执行注入。

**Why this priority**: SQL注入是OWASP Top 10安全风险之一，但当前接口使用内存比对而非数据库查询，注入风险相对较低，故设为P2。

**Independent Test**: 在 username 或 password 字段中嵌入常见 SQL 注入 payload 发送请求，独立验证注入字符不会被特殊处理且认证不被绕过。

**Acceptance Scenarios**:

1. **Given** 登录接口 /api/login 可用，**When** 发送 POST 请求且 body 包含 `{"username":"'"'"' OR '"'"'1'"'"'='"'"'1","password":"123456"}`，**Then** 返回 `code: 401`，认证不被绕过
2. **Given** 登录接口可用，**When** 发送 POST 请求且 password 字段包含 SQL 注入字符，**Then** 返回 `code: 401`，系统不抛出异常
3. **Given** 登录接口可用，**When** 发送 POST 请求且在字段中嵌入 SQL 关键字如 `DROP`、`SELECT`、`UNION`，**Then** 系统将其视为普通字符串，认证逻辑不变

---

### User Story 5 - 高频请求压力测试 (Priority: P2)

作为系统运维人员，当登录接口在短时间内收到大量并发请求时，系统应保持稳定，不出现崩溃或长时间无响应。

**Why this priority**: 高频请求测试验证系统的稳定性与限流能力，对于生产环境至关重要，但非当前开发阶段的核心功能路径。

**Independent Test**: 使用测试工具（如 Apache Bench、wrk 或自定义脚本）以 10 次/秒的速率发送 100 个连续请求，独立验证系统稳定性。

**Acceptance Scenarios**:

1. **Given** 登录接口 /api/login 可用，**When** 以 10 次/秒的频率连续发送 100 个 POST 请求，**Then** 所有请求均在 5 秒内返回响应，无连接超时
2. **Given** 登录接口在承受高频请求，**When** 全部请求完成，**Then** 服务进程不崩溃，后续正常请求仍可正确处理
3. **Given** 高频请求进行中，**When** 监测响应状态码分布，**Then** 不出现 5xx 服务器错误

---

### Edge Cases

- 请求体为非法 JSON 格式（如未闭合的花括号、多余逗号）时，系统应返回 400 Bad Request
- 用户名包含 Unicode/多字节字符（如中文"管理员"）时，系统应正确处理
- 请求包含 Content-Type 以外的 HTTP 头部（如 Accept-Encoding）变化时，系统行为应一致
- 密码字段包含超过 1000 个字符的超长输入时，系统不应崩溃
- 请求方法使用 GET 而非 POST 访问 /api/login 时，应返回 405 Method Not Allowed

## Requirements

### Functional Requirements

- **FR-001**: 系统必须正确验证用户名 admin 与密码 123456 的匹配关系，匹配成功时返回成功响应
- **FR-002**: 系统必须在用户名或密码不匹配时返回认证失败响应，不得泄露具体哪个字段错误
- **FR-003**: 系统必须在校验前验证 username 和 password 字段存在且非空，空值或缺失时应返回 400
- **FR-004**: 系统必须将所有输入作为普通字符串处理，不得解释或执行嵌入的 SQL/脚本语句
- **FR-005**: 系统必须在持续高频请求下保持稳定运行，不得崩溃或产生 5xx 状态码

### Key Entities

- **登录请求 (LoginRequest)**：包含 username 和 password 两个字段的 JSON 对象
- **登录响应 (LoginResponse)**：包含 code（状态码）、message（提示信息）、token（认证令牌）三个字段的 JSON 对象

## Success Criteria

### Measurable Outcomes

- **SC-001**: 使用正确凭据 admin/123456 的登录请求在 100% 的测试中返回 code=200
- **SC-002**: 所有错误密码场景返回 code=401，无任何场景绕过认证
- **SC-003**: 所有空参数场景返回 code=400，响应体中包含明确的错误描述
- **SC-004**: SQL 注入 payload 的认证绕过成功率为 0%
- **SC-005**: 以 10 次/秒持续发送 100 个请求，无连接超时，服务进程零崩溃

## Assumptions

- 登录接口已部署并可通过 HTTP 访问，默认监听 8080 端口
- 测试环境与生产环境隔离，压力测试不会影响真实用户
- 当前接口使用硬编码的凭据比对而非数据库查询，因此 SQL 注入风险天然较低
- 高频测试场景假设服务端无外部限流组件（如 Nginx rate limit、API Gateway），仅测试应用自身稳定性
- 接口使用 JSON 格式传输数据，Content-Type 为 application/json
- 测试工具（如 Apache Bench 或等价工具）在测试环境中可用
- [NEEDS CLARIFICATION]: 空用户名/空密码的期望 HTTP 状态码——当前需求明确要求返回 400，但已有代码实现返回的是 401。需要确认是否修改现有实现以满足此规范