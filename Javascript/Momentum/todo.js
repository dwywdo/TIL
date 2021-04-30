const toDoForm = document.querySelector(".js-toDoForm")
const toDoInput = toDoForm.querySelector("input")
const toDoList = document.querySelector(".js-toDoList")

const TODOS_LOCAL_STORAGE = 'toDos'

let toDos = [];
let itemId = 1;

function deleteTodo(event) {
    const btn = event.target;
    const li = btn.parentNode;
    toDoList.removeChild(li);
    const cleanToDos = toDos.filter(function(toDo) {
        return toDo.id !== parseInt(li.id);
    })
    toDos = cleanToDos;
    saveTodos();
}

function saveTodos() {
    /**
     * Localstorage's value can only be a string
     */
    localStorage.setItem(TODOS_LOCAL_STORAGE, JSON.stringify(toDos));
}

function loadTodos() {
    const loadedTodos = localStorage.getItem(TODOS_LOCAL_STORAGE);
    if (loadedTodos !== null) {
        const parsedToDos = JSON.parse(loadedTodos);
        parsedToDos.forEach(function (toDo) {
            paintTodo(toDo.text);
        })
    }
}

function paintTodo(text) {
    const delBtn = document.createElement("button");
    delBtn.innerHTML = "❌";
    delBtn.addEventListener("click", deleteTodo);

    const span = document.createElement("span");
    span.innerText = text;

    const li = document.createElement("li");
    const newId = itemId;
    itemId += 1;
    li.appendChild(span);
    li.appendChild(delBtn);
    li.id = newId;

    toDoList.appendChild(li);
    const toDoObj = {
        text: text,
        id: newId
    }
    toDos.push(toDoObj);
    saveTodos();
}

function handleTodoSubmit(event) {
    event.preventDefault();
    const currentValue = toDoInput.value;
    paintTodo(currentValue);
    toDoInput.value = "";
}

function init() { 
    loadTodos();
    toDoForm.addEventListener("submit", handleTodoSubmit)
}

init();