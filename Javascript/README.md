## Javascript
---

- Meaning of ES
  - Specification
  - Each browers implements accroding to provided these specficiation in their own ways
  - 

- Vanilla Javacsript
  - http://vanilla-js.com/

- Usage of Javascript
  - Website
  - Webapp
  - Desktop app
  - ...

- Useful link for javascript
  - https://repl.it

- JS files should be at the bottom of body tag

- Every expression should be at 1 line
  - To separate each line, use ;
  ```javascript
  a = 221;
  b = a - 5;
  console.log(b);
  ```

## let / const / v
---
- Create
- Initialize
- Use

- Creation & Initialization can be done at once

### let

Ref: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/let

Declares a block-scoped local variable, optionally initializing it to a value.

### const

Ref: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/const

is block-scoped, much like variables declared using the let keyword. The value of a constant can't be changed through reassignment, and it can't be redeclared.

### var

Ref: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/var

Declares a function-scoped or globally-scoped variable, optionally initializing it to a value.

### let vs. var?

- var hoisting: `var` declarations are processed before any code is executed.
- The scope of a variable with `var` is current `execution context` and `closures thereof`
  - `enclosing function and functions declared with ti`
  - For variables outside any function, `global`

- Good example for var variable's scope
    ```javascript
    var x = 0; // Declares x within file scope, then assigns it a value of 0.

    console.log(typeof z); // "undefined", since z doesn't exist yet

    function a() {
    var y = 2; // Declares y within scope of function a, then assigns it a value of 2.

    console.log(x, y); // 0 2

    function b() {
        x = 3; // Assigns 3 to existing file scoped x.
        y = 4; // Assigns 4 to existing outer y.
        z = 5; // Creates a new global variable z, and assigns it a value of 5.
            // (Throws a ReferenceError in strict mode.)
    }

    b(); // Creates z as a global variable.
    console.log(x, y, z); // 3 4 5
    }

    a(); // Also calls b.
    console.log(x, z);     // 3 5
    console.log(typeof y); // "undefined", as y is local to function a
    ```
