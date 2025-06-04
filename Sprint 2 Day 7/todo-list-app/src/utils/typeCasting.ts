// Utility functions for typecasting in the todo list application

// Type guard to check if an object is of type ITodo
function isITodo(obj: any): obj is ITodo {
    return typeof obj === 'object' && 
           typeof obj.id === 'number' && 
           typeof obj.title === 'string' && 
           typeof obj.completed === 'boolean';
}

// Function to cast a generic object to ITodo type
function castToITodo(obj: any): ITodo {
    if (isITodo(obj)) {
        return obj;
    }
    throw new Error("Invalid ITodo object");
}

// Generic function to safely cast types
function safeCast<T>(value: any): T {
    return value as T;
}

// Example usage of the utility functions
const exampleObj = { id: 1, title: "Sample Todo", completed: false };

try {
    const todoItem = castToITodo(exampleObj);
    console.log("Successfully casted to ITodo:", todoItem);
} catch (error) {
    console.error(error);
}