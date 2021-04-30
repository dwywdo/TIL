const body = document.querySelector("body");

const IMG_COUNT = 3;

function handleImageLoad() {
    console.log('finished loading');
}

function paintImage(imgNumber) {
    const image = new Image();
    image.src = `images/${imgNumber +1}.jpg`;
    image.classList.add("bgImage");
    body.appendChild(image);
}

function genRandom() {
    const number = Math.floor(Math.random()*IMG_COUNT);
    return number;
}
function init() {
    const randomNumber = genRandom();
    paintImage(randomNumber);
}

init();