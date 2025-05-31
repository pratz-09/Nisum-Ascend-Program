// Data Types: string, number, boolean, object, array

// To-Do Item Constructor Function (Object & Hoisting)
function TodoItem(description) {
    this.description = description;
    this.completed = false;
}

// Array to store to-do items
let todoList = [];

// Function to add a new to-do
function addTodo(description) {
    let item = new TodoItem(description);
    todoList.push(item);
    console.log(`Added: "${description}"`);
}

// Function to list all to-dos
function listTodos() {
    if (todoList.length === 0) {
        console.log("No to-do items.");
        return;
    }
    console.log("To-Do List:");
    for (let i = 0; i < todoList.length; i++) { // Control Flow: for loop
        let status = todoList[i].completed ? "✓" : "✗";
        console.log(`${i + 1}. [${status}] ${todoList[i].description}`);
    }
}

// Function to mark a to-do as completed
function completeTodo(index) {
    if (index < 1 || index > todoList.length) { // Control Flow: if statement
        console.log("Invalid to-do number.");
        return;
    }
    todoList[index - 1].completed = true;
    console.log(`Marked "${todoList[index - 1].description}" as completed.`);
}

// Function to remove a to-do
function removeTodo(index) {
    if (index < 1 || index > todoList.length) {
        console.log("Invalid to-do number.");
        return;
    }
    let removed = todoList.splice(index - 1, 1); // Array method
    console.log(`Removed: "${removed[0].description}"`);
}

// Example usage:
addTodo("Learn JavaScript data types");
addTodo("Practice functions and control flow");
addTodo("Understand hoisting");
listTodos();
completeTodo(2);
listTodos();
removeTodo(1);
listTodos();

// Hoisting Example
hoistedFunction();
function hoistedFunction() {
    console.log("This function is hoisted!");
}