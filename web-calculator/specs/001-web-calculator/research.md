# Research: Web Calculator

## Technical Decisions

### Decision 1: Single-file architecture
- **Decision**: All code in a single `index.html` with embedded `<style>` and `<script>`
- **Rationale**: Zero setup — open file in browser, calculator works immediately. Aligns with Constitution
  Principle I (Zero Dependencies) and Principle V (Simplicity). No build tools, no server needed.
- **Alternatives considered**: Multi-file split (`index.html` + `style.css` + `script.js`) — slightly better
  for maintainability in larger projects, but adds unnecessary complexity for a ~200-line app.

### Decision 2: CSS Grid for button layout
- **Decision**: Use CSS Grid for the calculator button grid (4 columns) and Flexbox for the display area
- **Rationale**: CSS Grid naturally supports the 4×4 + bottom row calculator layout. Flexbox handles the
  display alignment and centering. Aligns with Constitution Principle II (Responsive Design).
- **Alternatives considered**: CSS Grid alone is sufficient — no need for float or table-based layouts.

### Decision 3: JavaScript calculation approach
- **Decision**: Parse input expression and evaluate using a lightweight operator-precedence parser (two-stack
  algorithm: operands and operators) or a simpler sequential left-to-right evaluation for basic calculator
- **Rationale**: Standard calculators typically evaluate left-to-right without operator precedence
  (non-scientific mode). Simple state machine with current value, pending operator, and pending operand.
- **Alternatives considered**: `eval()` — insecure and against best practices. Full shunting-yard algorithm —
  overkill for basic four-function calculator.

### Decision 4: Dark theme color scheme
- **Decision**: Dark theme with high-contrast button colors (dark background, light text, accent colors for
  operators/equals)
- **Rationale**: Modern aesthetic; reduces eye strain; common calculator design pattern.
- **Alternatives considered**: Light theme — also viable, user preference. Sticking with dark as default.

### Decision 5: Keyboard event handling
- **Decision**: Use `keydown` event with a mapping object from `event.key` values to calculator actions
- **Rationale**: Simple, readable mapping. `keydown` fires before `keypress` and captures all keys
  (including Backspace, Escape, Enter) reliably.
- **Alternatives considered**: `keyup` — too late for Backspace default behavior prevention.
  `keypress` — deprecated and doesn't fire for all keys.

## Key Findings

- CSS Grid provides native responsive layout — `grid-template-columns: repeat(4, 1fr)` auto-scales buttons
- `toFixed()` or `Math.round()` handles floating-point precision (e.g., `0.1 + 0.2 = 0.3`)
- `event.preventDefault()` on Backspace avoids browser navigation
- Responsive design achieved via `min-width`/`max-width` on the calculator container + viewport-relative units
- No polyfills needed — modern browser target means full CSS Grid and ES6 support