# Data Model: Web Calculator

## Overview

The calculator is a **stateless, single-user** application with no persistent storage. All state exists
in-memory within the JavaScript runtime and resets on page refresh.

## State Machine

The calculator operates as a finite state machine with three states:

```
                ┌─────────────────────────────────────┐
                │                                     │
                ▼                                     │
         ┌───────────┐     press operator      ┌───────────┐
         │           │ ───────────────────────► │           │
         │  ENTERING  │                         │  STORED   │
         │  OPERAND   │ ◄────────────────────── │  OPERATOR │
         │           │     press digit/dec      │           │
         └───────────┘                         └───────────┘
              │                                      │
              │ press "="                            │ press "="
              ▼                                      ▼
         ┌─────────────────────────────────────────────────┐
         │                    RESULT                        │
         │  (press digit to start new; press operator to    │
         │   continue calculation from result)              │
         └─────────────────────────────────────────────────┘
              │
              │ press "C"
              ▼
         ┌───────────┐
         │   INIT    │  (display shows "0")
         └───────────┘
```

## State Variables

| Variable | Type | Description | Initial Value |
|----------|------|-------------|---------------|
| `currentInput` | String | Current display text being entered | `"0"` |
| `previousOperand` | Number | First operand stored when operator is pressed | `null` |
| `pendingOperator` | String | Last operator pressed (`+`, `-`, `*`, `/`) | `null` |
| `shouldResetDisplay` | Boolean | Whether next digit press should clear display | `false` |
| `hasError` | Boolean | Whether calculator is in error state | `false` |

## State Transitions

### Digit press (0-9)
- If `shouldResetDisplay` or `hasError`: replace display with digit
- If display is `"0"`: replace with digit (no leading zeros)
- Otherwise: append digit to display

### Decimal point press (.)
- If `shouldResetDisplay` or `hasError`: replace display with `"0."`
- If display already contains `.`: ignore
- Otherwise: append `.`

### Operator press (+, −, ×, ÷)
- If `hasError`: ignore
- If `pendingOperator` is set: compute pending operation first, then store new operator
- Otherwise: store current display as `previousOperand`, set `pendingOperator`

### Equals press (=)
- If `pendingOperator` is set: compute result from `previousOperand` and `currentInput`
- Handle division by zero → set error state
- Clear `pendingOperator` and `previousOperand`
- Set `shouldResetDisplay = true`

### Clear press (C)
- Reset all state variables to initial values

### Backspace press (⌫)
- If `hasError` or `shouldResetDisplay`: no effect (reset via C first)
- If display has single digit or is negative single digit: reset to `"0"`
- Otherwise: remove last character

## Error States

| Condition | Display | Recovery |
|-----------|---------|----------|
| Division by zero | `"错误"` | Press C to reset |
| Overflow (>10^15) | Truncated/scientific notation | Continue from result or C to reset |