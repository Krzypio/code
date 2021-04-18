/*let js = 'amazing';
console.log(1 - 10 * 10);

console.log("Jonas");
console.log(23);

let firstName = "Matilda";
console.log(firstName);
console.log(firstName);
console.log(firstName);

let PI = 3;
let asd = 3;
asd = 3;
PI = 2;
console.log(PI);*/
/*
true;
let javastriptIsFun = true;
console.log(javastriptIsFun);
console.log(typeof javastriptIsFun);
*/

/*console.log('Sarah can start driving license 🚗');
const age = 18;
console.log(`Hello ${age}`);
console.log(18 == '18')

const favouritle = Number(prompt("What is your favourite number?"));
console.log(favouritle);

if (favouritle === 23) {
    console.log("cool! 23 is an amazing number");
} else {
    console.log("You are not 23");
}
*/
/*const age = 18;
age >= 18 ? console.log("I like to drink wine 🍷") :
    console.log('I like to drink water 💦');

const drink = age >= 18 ? 'wine 🍷' : 'water 💦';
console.log(drink);

console.log(`I like to drink ${age >= 18 ? 'wine 🍷' : 'water 💦'}`);
*/
const billValue = (Number(prompt("How much is the bill?")));
const tipCoefficient = billValue >= 50 && billValue <= 300 ? 0.15 : 0.2;
const tipValue = billValue * tipCoefficient;

console.log(`The bill was ${billValue}, the tip was ${tipValue} (${tipCoefficient}), and the total value ${billValue + tipValue}`);