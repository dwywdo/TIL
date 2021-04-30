/**
 * document is an also object
 * DOM? Document Object Model
 */

alert("wait!")
const title = document.getElementById("title")
title.innerHTML = "Hi! From JS"
title.style.color = 'red';
console.dir(title)
console.dir(document)
document.title = "I own you now.."

const titleByQuerySelector = document.querySelector("#title")
console.log(titleByQuerySelector)


/**
 * For event handling function, you should pass handleResizing, not handleResizing()
 * https://developer.mozilla.org/en-US/docs/Web/Events
 */

function handleResizing(event) {
    console.log("You're resizing now..")
    console.log(event);
}

function handleClick(event) {
    title.style.color ="blue";
    console.log(event);
}
// window.addEventListener("resize", handleResizing)
// window.addEventListener("click", handleClick)
const BASE_COLOR = "rgb(52, 73, 94)";
const OTHER_COLOR = "#7f8c8d";

function toggleColor(event) {
    const currentColor = title.style.color;

    if (currentColor === BASE_COLOR) {
        title.style.color = OTHER_COLOR;
    }
    else {
        title.style.color = BASE_COLOR;
    }
}

// window.addEventListener("click", toggleColor)

function init() {
    title.style.color = BASE_COLOR;
    title.addEventListener("click", toggleColor);
    title.addEventListener("mouseover", toggleColor);
}

init();