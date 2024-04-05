const arr1 = [1, 2];
const arr2 = [3, 1, 3];
const summaryFunc = (num1, num2, num3) => console.log(num1 + num2 + num3);

summaryFunc(...arr1);
summaryFunc(...arr2);