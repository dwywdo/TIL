console.log("Hello world!")
let a = 221
let b = a - 5 
console.log(b, a)

const c = 1
let d = c + 1
// c = 5; // TypeError: Assignment to constant variable.

var e = 221
var f = e - 5
console.log(f, e)

console.log(x);
console.log('still going...');
var x = 1;
console.log(x);
console.log('still going...');

var y = 1;
console.log(globalThis.hasOwnProperty('y'))
delete globalThis.y; // Fails silently
delete y; // Fails silently

/** In the following,
 * var m = n, n = 'N';
 * m & n exists before they're declared before any code is executed
 * So no ReferenceError occurs
 * m <- Undefined, n <- 'N'
 */

var m = n, n = 'N';
console.log(m + n);


// String example
const string_example = "Nicolas";
console.log(string_example);

// Boolean example. Boolean is not text
const boolean_true_example = true;
const boolean_false_example = false;
console.log(boolean_true_example);
console.log(boolean_true_example);

// Number
const number_example = 5;
console.log(number_example);

// Float
const float_example = 3.5;
console.log(float_example);

/**
 * Arrays
 * Arrays can have different types of data as their elements
 */
const monday = "Mon";
const tuesday = "Tue";
const wednesday = "Wed";
const thursday = "Thu";
const friday = "Fri";
const satureday = "Sat";
const sunday = "Sun";

const daysOfWeek = [monday, tuesday, wednesday, thursday, friday, satureday, sunday]
console.log(monday, tuesday, wednesday, thursday, friday)
console.log(daysOfWeek);
console.log(daysOfWeek.length)

console.log(daysOfWeek[2])
console.log(daysOfWeek[432]) // undefined

/**
 * Objects
 * Difference of object & arrays is that 
 * objects can have their element's names for each
 */

const person_info = {
    name: "euiyub jung",
    age: 31,
    gender: "Male",
    favMovies: ["Along The Gods", "LOTR", "Iron Man"],
    favFoods: [
        {
            name: "Kimchi", 
            fatty: false
        },
        {
            name: "Cheese Burger",
            fatty: true
        }
    ]
}
console.log(person_info)
console.log(person_info.name)
console.log(person_info.age)
console.log(person_info.gender)
console.log(person_info)

// Even though an object is declared as const, 
// we can changed interal property's value
// But we can't changed a referece of object
person_info.gender = "Female"
console.log(person_info.gender)
