const weatherContainer = document.querySelector(".js-weahter")

const API_KEY = "80befd58fb452625490605eb17c3e82f";
const COORDS = 'coords';

function getWeather(lat, lon) {
    fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric`)
    .then(function(response) {
        return response.json()
    }).then(function(json) {
        console.log(json);
        const temperature = json.main.temp;
        const place = json.name;
        weatherContainer.innerHTML=`${temperature} @ ${place}`
    })
}

function saveCoords(coordsObj) {
    console.log(coordsObj);
    localStorage.setItem(COORDS, JSON.stringify(coordsObj));
}

function handleGeoSuccess(position) {
    console.log(position);
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;
    const coordsObj = {
        latitude,
        longitude
    };
    saveCoords(coordsObj);
    getWeather(latitude, longitude)
}

function handleGeoError() {
    console.log("Can't access geo location")
}

function askForCoords() {
    navigator.geolocation.getCurrentPosition(handleGeoSuccess, handleGeoError)    
}


function loadCoords() {
    const loadedCoords = localStorage.getItem(COORDS);
    if (loadedCoords === null) {
        askForCoords();
    }
    else {
        const parsedCoords = JSON.parse(loadedCoords);
        getWeather(parsedCoords.latitude, parsedCoords.longitude);
    }

}

function init() {
    loadCoords();
}

init();