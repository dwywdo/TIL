const title = document.querySelector("#title");

const CLICKED_CLASS = "clicked"

function handleClick() {
    const currentClasses = title.classList;
    if (!currentClasses.contains(CLICKED_CLASS)) {
        title.classList.add(CLICKED_CLASS)
    }
    else {
        title.classList.remove(CLICKED_CLASS)
    }
}

function toggleClick() {
    title.classList.toggle(CLICKED_CLASS)
}


function init() {
    // window.addEventListener("click", handleClick);
    window.addEventListener("click", toggleClick);
}

init();
