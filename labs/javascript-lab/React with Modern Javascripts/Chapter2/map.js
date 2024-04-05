const nameArr = ["Nushida", "Sakioka", "Goto"];

const newNameArr = nameArr.map((name) => {
    if (name === "Nushida") {
        return name;
    } else {
        return `${name} san`;
    }
})

console.log(newNameArr);