/**
 * querySelector vs. querySelectorAll
 */

const form = document.querySelector(".js-form");
const input = form.querySelector("input");
const greeting = document.querySelector(".js-greetings");

const USER_LOCAL_STORAGE = "currentUserName";
const SHOWING_CLASSNAME = "showing";

function saveName(text) {
    localStorage.setItem(USER_LOCAL_STORAGE, text);
}

function handleSubmit(event) {
    event.preventDefault();
    const currentValue = input.value;
    saveName(currentValue);
    paintGreeting(localStorage.getItem(USER_LOCAL_STORAGE))
}

function askForName() {
    form.classList.add(SHOWING_CLASSNAME);
    form.addEventListener("submit", handleSubmit)
}

function paintGreeting(text) {
    form.classList.remove(SHOWING_CLASSNAME)
    greeting.classList.add(SHOWING_CLASSNAME)
    greeting.innerHTML = `Hello ${text}`
}

function loadName() {
    const currentUserName = localStorage.getItem(USER_LOCAL_STORAGE);
    if (currentUserName === null){ 
        askForName();
    } else {
        paintGreeting(currentUserName);
    }
}
function init() {
    loadName();
}

init();