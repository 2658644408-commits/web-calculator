---

description: "Task list for Web Calculator feature implementation"

---

# Tasks: Web Calculator

**Input**: Design documents from `specs/001-web-calculator/`

**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Tests**: Not requested in spec — manual verification per quickstart.md

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2, US3)
- Include exact file paths in descriptions

## Path Conventions

- **Single project**: `index.html` at repository root (with embedded `<style>` and `<script>`)

---

## Phase 1: Setup

**Purpose**: Create the HTML skeleton and project file

- [X] T001 Create `index.html` with HTML5 boilerplate, embedded `<style>` and `<script>` blocks, and Chinese lang attribute

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: CSS layout and display that MUST be complete before ANY user story can be implemented

**⚠️ CRITICAL**: No user story work can begin until this phase is complete

- [X] T002 [P] Implement CSS Grid calculator button layout (4-column grid, + operator buttons) in `<style>` section of `index.html`
- [X] T003 [P] Implement display area with CSS Flexbox (right-aligned text, responsive font size, initial "0") in `<style>` section of `index.html`
- [X] T004 [P] Implement CSS custom properties for dark theme (background, button colors, operator accent, equals accent) in `<style>` section of `index.html`

**Checkpoint**: Foundation ready — user story implementation can now begin in parallel

---

## Phase 3: User Story 1 — 基本算术运算 (Priority: P1) 🎯 MVP

**Goal**: User can click numeric and operator buttons on the calculator and see correct results

**Independent Test**: Open `index.html`, click `7` → `+` → `3` → `=`, display shows `10`

### Implementation for User Story 1

- [X] T005 [P] [US1] Create calculator HTML button markup (0-9 digits, + − × ÷ operators, = equals, . decimal) in `<body>` of `index.html`
- [X] T006 [US1] Implement arithmetic state machine (currentInput, previousOperand, pendingOperator, shouldResetDisplay) in `<script>` section of `index.html`
- [X] T007 [US1] Implement number and decimal button click handlers that update display in `<script>` section of `index.html`
- [X] T008 [US1] Implement operator button click handlers that store pending operation and update display in `<script>` section of `index.html`
- [X] T009 [US1] Implement equals button handler that computes result and displays it in `<script>` section of `index.html`
- [X] T010 [US1] Implement display update function that renders currentInput onto the display element in `<script>` section of `index.html`
- [X] T011 [US1] Add button press visual feedback (`:active` CSS transform/opacity) in `<style>` section of `index.html`

**Checkpoint**: At this point, User Story 1 should be fully functional — all four operations work via mouse clicks

---

## Phase 4: User Story 2 — 键盘输入支持 (Priority: P2)

**Goal**: User can operate the calculator entirely via keyboard

**Independent Test**: Open `index.html`, press keys `9` → `*` → `3` → `Enter`, display shows `27`

### Implementation for User Story 2

- [X] T012 [US2] Implement `keydown` event listener with key-to-action mapping (0-9 → digits, +-*/ → operators, Enter/= → equals, Backspace → ⌫, Escape/Delete → C) in `<script>` section of `index.html`
- [X] T013 [US2] Add matching button highlight animation when keyboard input triggers a button action in `<script>` and `<style>` sections of `index.html`

**Checkpoint**: At this point, User Stories 1 AND 2 should both work — full mouse + keyboard input

---

## Phase 5: User Story 3 — 清除、退格与错误处理 (Priority: P2)

**Goal**: User can correct mistakes with clear/backspace and gets friendly error on division by zero

**Independent Test**: Type `12345`, press Backspace twice → shows `123`, press C → shows `0`. Type `7÷0=` → shows error

### Implementation for User Story 3

- [X] T014 [US3] Implement clear (C) button handler that resets all state to initial values (display `0`) in `<script>` section of `index.html`
- [X] T015 [US3] Implement backspace (⌫) button handler that removes last character (resets to `0` if only one digit) in `<script>` section of `index.html`
- [X] T016 [US3] Implement division by zero check — show user-friendly error message (e.g., `"错误"`) instead of `Infinity` in `<script>` section of `index.html`
- [X] T017 [US3] Implement edge case handling: ignore second decimal point, strip leading zeros (e.g., `007` → `7`), ignore empty equals, handle consecutive operators (take last operator) in `<script>` section of `index.html`

**Checkpoint**: All user stories should now be independently functional

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Responsive refinements and final validation

- [X] T018 Add responsive media queries for 320px (mobile), 768px (tablet), and 1280px+ (desktop) widths in `<style>` section of `index.html`
- [X] T019 Verify all interactions against quickstart.md quality checklist (5 validation scenarios, 8 checklist items)

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies — can start immediately
- **Foundational (Phase 2)**: Depends on Setup completion — BLOCKS all user stories
- **User Stories (Phase 3-5)**: All depend on Foundational phase completion
  - User stories can proceed in priority order (P1 → P2 → P2)
- **Polish (Final Phase)**: Depends on all desired user stories being complete

### User Story Dependencies

- **User Story 1 (P1)**: Can start after Foundational (Phase 2) — No dependencies on other stories
- **User Story 2 (P2)**: Can start after Foundational (Phase 2) — independently testable from US1
- **User Story 3 (P2)**: Can start after Foundational (Phase 2) — independently testable from US1 and US2

### Within Each User Story

- Implementation tasks within each story can run sequentially bottom-up
- All within a story modify the same `index.html` file — coordinate edits

### Parallel Opportunities

- All Foundational tasks marked [P] can run in parallel (within Phase 2)
- User stories US2 and US3 can be worked on in parallel after US1 completes

---

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Complete Phase 1: Setup → T001
2. Complete Phase 2: Foundational → T002, T003, T004
3. Complete Phase 3: User Story 1 → T005 through T011
4. **STOP and VALIDATE**: Open `index.html` and test all four arithmetic operations
5. MVP is ready for review

### Incremental Delivery

1. Complete Setup + Foundational → Foundation ready
2. Add User Story 1 → Test (MVP!) → User clicks buttons, gets results
3. Add User Story 2 → Test → Keyboard support
4. Add User Story 3 → Test → Clear/backspace/error handling
5. Add Polish → Responsive layout finalization

---

## Notes

- [P] tasks = different CSS custom properties / independent concerns
- [Story] label maps task to specific user story for traceability
- All tasks modify the same `index.html` file — manage edits carefully
- Commit after each logical group of tasks
- Stop at MVP checkpoint to validate story independently
