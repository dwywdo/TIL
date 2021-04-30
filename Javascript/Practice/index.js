function sayHello(name, age) {
    console.log("Hello, " + name + ". You are " + age + " years old.")
}

sayHello("Euiyub Jung", 31)

function sayHelloUsingBacktick(name, age) {
    console.log(`Hello, ${name}. You are ${age} years old.`)
}

sayHelloUsingBacktick("Euiyub Jung", 31)

function sayHelloReturn(name, age) {
    return `Hello, ${name}. You are ${age} years old.`
}

const greetEuiyub = sayHelloReturn("Euiyub Jung", 31)
console.log(greetEuiyub)

const calculator = {
    plus: function(op1, op2) {
        return op1 + op2
    },
    minus: function(op1, op2) {
        return op1 - op2
    },
    multiply: function(op1, op2) {
        return op1 * op2
    },
    divide: function(op1, op2) {
        return op1 / op2
    },
    powerOf: function(op1, op2) {
        return op1 ** op2
    }
}
console.log(calculator.plus(3, 5))
console.log(calculator.powerOf(2, 5))

