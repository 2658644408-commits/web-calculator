# Quickstart: Web Calculator

## Prerequisites

- A modern web browser (Chrome 90+, Firefox 88+, Safari 15+, Edge 90+)
- No server, no build tools, no dependencies required

## Setup

```bash
# Clone or navigate to the project root
cd web-calculator

# That's it — no install step needed
```

## Running the Calculator

### Option 1: Open directly from filesystem

Double-click `index.html` in the repository root, or open it via the browser's:

```
File → Open File → index.html
```

### Option 2: Serve via any static HTTP server (optional)

```bash
# Using Python 3 (no extra installs)
python -m http.server 8000

# Using Node.js (if npx is available)
npx serve .

# Then open http://localhost:8000 in your browser
```

## Validation Scenarios

### Scenario 1: Basic arithmetic (mouse)

| Step | Action | Expected Display |
|------|--------|-----------------|
| 1 | Open `index.html` | `"0"` |
| 2 | Click `7` | `"7"` |
| 3 | Click `+` | `"7"` (operator registered) |
| 4 | Click `3` | `"3"` |
| 5 | Click `=` | `"10"` |

### Scenario 2: Keyboard input

| Step | Action | Expected Display |
|------|--------|-----------------|
| 1 | Refresh page | `"0"` |
| 2 | Press `9` (keyboard) | `"9"` |
| 3 | Press `*` (keyboard) | `"9"` |
| 4 | Press `3` (keyboard) | `"3"` |
| 5 | Press `Enter` (keyboard) | `"27"` |

### Scenario 3: Clear and backspace

| Step | Action | Expected Display |
|------|--------|-----------------|
| 1 | Type `12345` | `"12345"` |
| 2 | Press Backspace twice | `"123"` |
| 3 | Press C | `"0"` |

### Scenario 4: Division by zero

| Step | Action | Expected Display |
|------|--------|-----------------|
| 1 | Type `7 ÷ 0` | `"7"`, `"7"`, `"0"` |
| 2 | Press = | Shows error message (e.g., `"错误"`) |
| 3 | Press C | `"0"` |

### Scenario 5: Responsive layout

| Step | Action | Expected |
|------|--------|----------|
| 1 | Resize browser to 320px width | Calculator fits without horizontal scroll |
| 2 | Resize to 768px | Calculator centered, proportionally larger |
| 3 | Resize to 1280px | Calculator centered with comfortable max-width |

## Quality Gate Checklist

Before considering the feature complete, verify:

- [ ] All 4 operations (+, −, ×, ÷) produce correct results
- [ ] Decimal calculations handle precision correctly (`0.1 + 0.2 = 0.3`)
- [ ] Division by zero shows error, not `Infinity`
- [ ] Keyboard input matches button clicks for every action
- [ ] Backspace removes last character only
- [ ] C resets everything to `"0"`
- [ ] Layout works at 320px, 768px, and 1280px widths
- [ ] No console errors on any interaction