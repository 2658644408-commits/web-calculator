# UI Behavior Contract: Calculator Display & Interaction

## Purpose

Defines the observable behavior contract between the calculator UI and the user. Every claim below
is independently verifiable by opening the HTML file in a browser.

## Display Contract

### Display Area
- Located at the top of the calculator
- Shows right-aligned text (current input or result)
- Shows `"0"` on initial load and after clear
- Shows error message (e.g., `"错误"`) on invalid operations
- Font size adapts to viewport width (responsive)

### Button Layout (4-column grid)

```
┌─────┬─────┬─────┬─────┐
│  7  │  8  │  9  │  ÷  │
├─────┼─────┼─────┼─────┤
│  4  │  5  │  6  │  ×  │
├─────┼─────┼─────┼─────┤
│  1  │  2  │  3  │  −  │
├─────┼─────┼─────┼─────┤
│  0  │  .  │  C  │  +  │
├─────┴─────┴─────┼─────┤
│       ⌫        │  =  │
└─────────────────┴─────┘
```

### Button Styling Contract
- Number buttons (0-9) and decimal (.): Default style (dark gray)
- Operator buttons (+, −, ×, ÷): Accent color (e.g., orange/amber)
- Equals (=): Distinct accent (e.g., orange/amber, same as operators or brighter)
- Clear (C): Different shade (e.g., lighter gray or red tint)
- Backspace (⌫): Similar to numbers or distinct
- All buttons: `cursor: pointer`, `user-select: none`, active press state (`:active` transform or shadow)

## Interaction Contract

| Action | Observable Result |
|--------|------------------|
| Click number button | Digit appears/appended on display |
| Click operator button | Operator registered; display shows first operand |
| Click = | Result of operation displayed |
| Click C | Display resets to "0"; all state cleared |
| Click ⌫ | Last character removed from display |
| Click . | Decimal point appended (ignored if already present) |
| Press 0-9 on keyboard | Same as clicking number button |
| Press +, -, *, / on keyboard | Same as clicking operator button |
| Press Enter or = | Same as clicking = |
| Press Backspace | Same as clicking ⌫ |
| Press Escape or Delete | Same as clicking C |
| Divide by zero | Display shows error message |
| Press C after error | Display resets to "0" |