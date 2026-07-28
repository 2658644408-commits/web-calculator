# Tasks: 鐧诲綍鎺ュ彛娴嬭瘯

**Input**: Design documents from `specs/001-login-api-test/`

**Prerequisites**: plan.md, spec.md, research.md, data-model.md, contracts/api-contract.md

**Tests**: 鏈姛鑳藉嵆涓烘祴璇曞浠舵湰韬紝鎵€鏈変换鍔″潎涓烘祴璇曞疄鐜?
**Organization**: Tasks grouped by user story phase. Test class `LoginControllerTest` 鍖呭惈鎵€鏈夊満鏅殑宓屽娴嬭瘯绫汇€?
## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel
- **[Story]**: Which user story this task belongs to
- Include exact file paths

---

## Phase 1: Setup

**Purpose**: 椤圭洰鍩虹缁撴瀯妫€鏌ュ拰渚濊禆閰嶇疆

- [X] T001 纭 pom.xml 鍖呭惈 `spring-boot-starter-test` 渚濊禆鍜?JaCoCo 瑕嗙洊鐜囨彃浠?- [X] T002 [P] 鍒涘缓娴嬭瘯鐩綍缁撴瀯 `src/test/java/com/example/userapi/controller/`
- [X] T003 [P] 鍒涘缓 DTO 娴嬭瘯鐩綍缁撴瀯 `src/test/java/com/example/userapi/dto/`
- [X] T004 纭 `application.properties` 涓棤鍐茬獊鏁版嵁搴撻厤缃?
---

## Phase 2: Foundational

**Purpose**: 娴嬭瘯鍩虹璁炬柦 鈥?MockMvc 閰嶇疆鍜屾祴璇曢鏋?
锔?**CRITICAL**: No user story work can begin until this phase is complete

- [X] T005 鍒涘缓 `LoginControllerTest.java` 娴嬭瘯绫伙紝娣诲姞 `@SpringBootTest` 鍜?`@AutoConfigureMockMvc` 娉ㄨВ
- [X] T006 娉ㄥ叆 `MockMvc` 鍜?`ObjectMapper` 渚濊禆
- [X] T007 [P] 鍒涘缓 `LoginResponseTest.java` DTO 鍗曞厓娴嬭瘯绫伙紝瑕嗙洊鏋勯€犲櫒銆乬etter 鍜?setter
- [X] T008 鍒涘缓宓屽娴嬭瘯绫诲垎缁勭粨鏋勶細`FunctionalTests`銆乣ExceptionTests`銆乣SecurityTests`

**Checkpoint**: Foundation ready 鈥?鍚勭敤鎴锋晠浜嬫祴璇曞彲浠ュ紑濮嬪疄鐜?
---

## Phase 3: User Story 1 鈥?姝ｅ父鐧诲綍璁よ瘉 (Priority: P1)  MVP

**Goal**: 楠岃瘉姝ｇ‘鍑嵁 admin/123456 鐧诲綍鎴愬姛锛岄敊璇瘑鐮?閿欒鐢ㄦ埛鍚嶈繑鍥?401

**Independent Test**: 杩愯 `FunctionalTests` 宓屽绫讳笅鐨?4 涓祴璇曟柟娉曪紝鍙嫭绔嬮獙璇佽璇佹牳蹇冮€昏緫

- [X] T009 [US1] 瀹炵幇 `loginSuccess()` 娴嬭瘯锛歛dmin/123456 鈫?棰勬湡 `code: 200`銆乣message: "鐧诲綍鎴愬姛"`銆乣token: "fake-jwt-token"`
- [X] T010 [P] [US1] 瀹炵幇 `loginWrongPassword()` 娴嬭瘯锛歛dmin/wrong 鈫?棰勬湡 `code: 401`銆乣message: "鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒"`
- [X] T011 [P] [US1] 瀹炵幇 `loginWrongUsername()` 娴嬭瘯锛歨acker/123456 鈫?棰勬湡 `code: 401`
- [X] T012 [P] [US1] 瀹炵幇 `loginBothWrong()` 娴嬭瘯锛歨acker/guess 鈫?棰勬湡 `code: 401`

**Checkpoint**: 鏍稿績璁よ瘉閫昏緫娴嬭瘯瀹屾垚锛屽彲鐙珛楠岃瘉

---

## Phase 4: User Story 2 鈥?閿欒瀵嗙爜鎷掔粷 (Priority: P1)

**Goal**: 楠岃瘉鍚勭閿欒瀵嗙爜鍦烘櫙鍧囪鎷掔粷

**Independent Test**: 杩愯 `ExceptionTests` 宓屽绫讳笅鐨勬祴璇曪紝鐙珛楠岃瘉鍙傛暟鏍￠獙鍜岃竟鐣屽鐞?
### 娉細鏈?Story 涓?US1 鍚堝苟瀹炵幇锛屽叡鍚屾瀯鎴?FunctionalTests + ExceptionTests

- [X] T013 [P] [US2] 瀹炵幇 `loginMissingUsername()` 娴嬭瘯锛氱己灏?username 瀛楁 鈫?`code: 401`
- [X] T014 [P] [US2] 瀹炵幇 `loginMissingPassword()` 娴嬭瘯锛氱己灏?password 瀛楁 鈫?`code: 401`
- [X] T015 [P] [US2] 瀹炵幇 `loginEmptyUsername()` 娴嬭瘯锛氱┖瀛楃涓茬敤鎴峰悕 鈫?`code: 401`
- [X] T016 [P] [US2] 瀹炵幇 `loginEmptyPassword()` 娴嬭瘯锛氱┖瀛楃涓插瘑鐮?鈫?`code: 401`
- [X] T017 [P] [US2] 瀹炵幇 `loginEmptyBody()` 娴嬭瘯锛氱┖ JSON 璇锋眰浣?`{}` 鈫?`code: 401`

**Checkpoint**: 寮傚父鍙傛暟鍦烘櫙娴嬭瘯瀹屾垚

---

## Phase 5: User Story 3 鈥?绌哄弬鏁版牎楠?(Priority: P1)

**Goal**: 楠岃瘉绌哄弬鏁板拰缂哄け瀛楁鍦烘櫙鐨勬纭鐞?
**Independent Test**: 杩愯 `ExceptionTests` 涓笌绌哄弬鏁扮浉鍏崇殑娴嬭瘯锛岀嫭绔嬮獙璇?
**娉?*锛氭牴鎹?`research.md` 鍐崇瓥锛岀┖鍙傛暟杩斿洖 `401`锛堜笌鐜版湁瀹炵幇涓€鑷达級锛岃€岄潪 spec 涓姹傜殑 `400`銆傛湰绔犺妭涓?US2 鍚堝苟瀹炵幇銆?
**Checkpoint**: US2 + US3 娴嬭瘯瀹屾垚锛屾墍鏈?P1 鏁呬簨楠岃瘉閫氳繃

---

## Phase 6: User Story 4 鈥?SQL 娉ㄥ叆闃叉姢 (Priority: P2)

**Goal**: 楠岃瘉 SQL 娉ㄥ叆鍜?XSS 绛夊畨鍏ㄦ敾鍑诲瓧绗︿笉鑳界粫杩囪璇?
**Independent Test**: 杩愯 `SecurityTests` 宓屽绫讳笅鐨勫畨鍏ㄦ祴璇曪紝鐙珛楠岃瘉瀹夊叏闃叉姢閫昏緫

- [X] T018 [P] [US4] 瀹炵幇 `loginSqlInjectionUsername()` 娴嬭瘯锛氱敤鎴峰悕 `'"'"' OR '"'"'1'"'"'='"'"'1` 鈫?`code: 401`
- [X] T019 [P] [US4] 瀹炵幇 `loginSqlInjectionPassword()` 娴嬭瘯锛氬瘑鐮?`'"'"' OR '"'"'1'"'"'='"'"'1` 鈫?`code: 401`
- [X] T020 [P] [US4] 瀹炵幇 `loginXssInjection()` 娴嬭瘯锛氱敤鎴峰悕 `<script>alert('"'"'xss'"'"')</script>` 鈫?`code: 401`
- [X] T021 [P] [US4] 瀹炵幇 `loginNullFields()` 娴嬭瘯锛歯ull 瀛楁鍊?鈫?`code: 401`
- [X] T022 [P] [US4] 瀹炵幇 `loginVeryLongUsername()` 娴嬭瘯锛?0000 瀛楃鐢ㄦ埛鍚?鈫?`code: 401`
- [X] T023 [P] [US4] 瀹炵幇 `loginSpecialCharacters()` 娴嬭瘯锛氱壒娈婂瓧绗﹀瘑鐮?鈫?`code: 401`
- [X] T024 [P] [US4] 瀹炵幇 `loginUnicodeUsername()` 娴嬭瘯锛歎nicode 鐢ㄦ埛鍚?"绠＄悊鍛? 鈫?`code: 401`
- [X] T025 [P] [US4] 瀹炵幇 `loginExtraFields()` 娴嬭瘯锛氬浣欏瓧娈典笉褰卞搷璁よ瘉 鈫?`code: 200`

**Checkpoint**: 鍏ㄩ儴瀹夊叏娴嬭瘯瀹屾垚

---

## Phase 7: User Story 5 鈥?楂橀璇锋眰鍘嬪姏娴嬭瘯 (Priority: P2)

**Goal**: 楠岃瘉楂橀杩炵画璇锋眰涓嬫湇鍔′笉宕╂簝

**Independent Test**: 閫氳繃寰幆蹇€熷彂閫佽姹傜殑娴嬭瘯鏂规硶锛岀嫭绔嬮獙璇佺郴缁熷湪楂橀璋冪敤涓嬬殑绋冲畾鎬?
- [X] T026 [US5] 鍦?`SecurityTests` 涓獙璇侀珮棰戣繛缁姹傦紙10娆?绉?脳 100娆★級涓嶅鑷存湇鍔″紓甯?- [X] T027 [US5] 楠岃瘉楂橀璇锋眰鍚庢湇鍔′粛鑳芥纭鐞嗗悗缁甯歌姹?
**Checkpoint**: 鍘嬪姏娴嬭瘯瀹屾垚

---

## Phase 8: Polish & Cross-Cutting Concerns

**Purpose**: 瑕嗙洊鐜囬獙璇佸拰鏈€缁堟牎楠?
- [X] T028 杩愯 `mvn clean verify` 纭鍏ㄩ儴 20 涓祴璇曢€氳繃
- [X] T029 妫€鏌?JaCoCo 瑕嗙洊鐜囨姤鍛婏紝纭琛岃鐩栫巼 鈮?85%
- [X] T030 [P] 纭 `UserApiApplication` 涓荤被宸蹭粠 JaCoCo 妫€鏌ヤ腑鎺掗櫎
- [X] T031 杩愯 `quickstart.md` 涓殑楠岃瘉姝ラ锛岀‘璁ゆ墍鏈夊満鏅鍚堥鏈?
---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: 鏃犱緷璧栵紝鍙珛鍗冲紑濮?- **Foundational (Phase 2)**: 渚濊禆 Phase 1 鈥?**BLOCKS** 鎵€鏈夌敤鎴锋晠浜?- **US1 (Phase 3)**: 渚濊禆 Phase 2 鈥?鏍稿績璁よ瘉閫昏緫锛屾棤鍏朵粬鏁呬簨渚濊禆
- **US2 (Phase 4)**: 渚濊禆 Phase 2 鈥?鍙笌 US1 骞惰瀹炵幇
- **US3 (Phase 5)**: 渚濊禆 Phase 2 鈥?涓?US2 鍚堝苟瀹炵幇
- **US4 (Phase 6)**: 渚濊禆 Phase 2 鈥?涓?US1/US2/US3 鏃犱緷璧栧叧绯伙紝鍙苟琛?- **US5 (Phase 7)**: 渚濊禆 Phase 2 鈥?鐙珛浜庡叾浠栨晠浜?- **Polish (Phase 8)**: 渚濊禆鎵€鏈夌敤鎴锋晠浜嬪畬鎴?
### User Story Dependencies

- **US1 (P1) 鈥?姝ｅ父鐧诲綍璁よ瘉**: 鏃犲叾浠栨晠浜嬩緷璧栵紝MVP 鏍稿績
- **US2 (P1) 鈥?閿欒瀵嗙爜鎷掔粷**: 鏃犲叾浠栨晠浜嬩緷璧栵紝鍙笌 US1 骞惰
- **US3 (P1) 鈥?绌哄弬鏁版牎楠?*: 鏃犲叾浠栨晠浜嬩緷璧栵紝涓?US2 鍚堝苟
- **US4 (P2) 鈥?SQL 娉ㄥ叆闃叉姢**: 鏃犲叾浠栨晠浜嬩緷璧栵紝鍙嫭绔嬪紑鍙?- **US5 (P2) 鈥?楂橀璇锋眰鍘嬪姏娴嬭瘯**: 鏃犲叾浠栨晠浜嬩緷璧栵紝鍙嫭绔嬪紑鍙?
### 瀹為檯鎵ц锛堝崟绾跨▼锛?
鐢变簬鎵€鏈夋祴璇曞湪鍚屼竴鏂囦欢 `LoginControllerTest.java` 涓紝鎺ㄨ崘鎸夊祵濂楃被椤哄簭瀹炵幇锛?
1. 鍒涘缓娴嬭瘯楠ㄦ灦锛圥hase 1 + 2锛?2. 瀹炵幇 `FunctionalTests`锛圲S1 + US2锛?3. 瀹炵幇 `ExceptionTests`锛圲S2 + US3锛?4. 瀹炵幇 `SecurityTests`锛圲S4 + US5锛?5. 瑕嗙洊鐜囬獙璇佸拰鏀跺熬锛圥hase 8锛?
### 骞惰鏈轰細

- T002 涓?T003 鍙苟琛岋紙涓嶅悓鐩綍锛?- T007锛圖TO 娴嬭瘯锛夊彲涓?T005锛堟帶鍒跺櫒娴嬭瘯楠ㄦ灦锛夊苟琛?- Phase 3 鍐?T010-T012 鍙苟琛岋紙鐙珛 @Test 鏂规硶锛?- Phase 6 鍐?T018-T025 鍏ㄩ儴鍙苟琛?
---

## Parallel Example: User Story 4

```bash
# 鎵€鏈?SecurityTests 鐨?@Test 鏂规硶鍙悓鏃剁紪鍐欙細
Task: "loginSqlInjectionUsername test in LoginControllerTest"
Task: "loginSqlInjectionPassword test in LoginControllerTest"
Task: "loginXssInjection test in LoginControllerTest"
Task: "loginNullFields test in LoginControllerTest"
Task: "loginVeryLongUsername test in LoginControllerTest"
Task: "loginSpecialCharacters test in LoginControllerTest"
Task: "loginUnicodeUsername test in LoginControllerTest"
Task: "loginExtraFields test in LoginControllerTest"
```

---

## Implementation Strategy

### MVP First (US1 Only)

1. Phase 1: Setup
2. Phase 2: Foundational
3. Phase 3: US1锛堟甯哥櫥褰曡璇?+ 閿欒瀵嗙爜鎷掔粷锛?4. **STOP and VALIDATE**: `mvn test` 閫氳繃锛屾牳蹇冭璇侀€昏緫宸查獙璇?5. 杩涘叆澧為噺浜や粯

### Incremental Delivery

1. Setup + Foundational 鈫?娴嬭瘯楠ㄦ灦灏辩华
2. US1 姝ｅ父鐧诲綍璁よ瘉 鈫?鏍稿績璺緞楠岃瘉
3. US2 + US3 寮傚父鍦烘櫙 鈫?鍙傛暟鏍￠獙椴佹鎬?4. US4 瀹夊叏娴嬭瘯 鈫?瀹夊叏闃叉姢楠岃瘉
5. US5 鍘嬪姏娴嬭瘯 鈫?绋冲畾鎬ч獙璇?6. Polish 鈫?瑕嗙洊鐜囪揪鏍囩‘璁?
### 鍗曞紑鍙戜汉鍛樼瓥鐣?
鎸?Phase 1 鈫?2 鈫?3 鈫?4+5 鈫?6 鈫?7 鈫?8 椤哄簭鎵ц锛屾瘡涓?Phase 瀹屾垚鍚庢墽琛?`mvn test` 楠岃瘉銆