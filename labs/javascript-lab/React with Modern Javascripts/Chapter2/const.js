const val3 = "const 변수";
console.log(val3)

// val3 = "const 변수 덮어쓰기";
// TypeError: Assignment to constant variable.

// const val3 = "const 변수 재선언";
// SyntaxError: Identifier 'val3' has already been declared

const obj1 = {
    name: "Nushida",
    age: 24,
};

console.log(obj1);

obj1.name = "Euiyub";
console.log(obj1);

obj1.address = "Tokyo";
console.log(obj1);


const arr1 = ["dog", "cat"];
console.log(arr1);

arr1[0] = "bird";
console.log(arr1);

arr1.push("monkey");
console.log(arr1);