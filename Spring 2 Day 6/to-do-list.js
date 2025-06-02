// to-do-list.js

// Symbol for private property
const _tasks = Symbol('tasks');

// Task class using enhanced object literals, default parameters, destructuring, and Object.assign
class Task {
    constructor({ id, title, completed = false } = {}) {
        Object.assign(this, { id, title, completed });
    }
}

// TodoList class using ES6 features
class TodoList {
    constructor() {
        this[_tasks] = new Map();
        this._idCounter = 1;
    }

    // Add a task (uses default parameter, arrow function, template literals)
    addTask = (title = 'Untitled Task') => {
        const id = this._idCounter++;
        const task = new Task({ id, title });
        this[_tasks].set(id, task);
        return task;
    };

    // Remove a task
    removeTask = (id) => this[_tasks].delete(id);

    // Toggle task completion
    toggleTask = (id) => {
        const task = this[_tasks].get(id);
        if (task) task.completed = !task.completed;
        return task;
    };

    // Get all tasks (uses Array.from, spread operator)
    getTasks = () => Array.from(this[_tasks].values());

    // Iterator using for...of and generator
    *[Symbol.iterator]() {
        for (const task of this.getTasks()) {
            yield task;
        }
    }

    // Find tasks by completion status (uses rest operator)
    findTasksByStatus = (...statuses) =>
        this.getTasks().filter(({ completed }) => statuses.includes(completed));

    // Use Object.is to compare tasks
    isSameTask = (taskA, taskB) => Object.is(taskA.id, taskB.id);

    // Use Array.of to create an array of tasks by ids
    getTasksByIds = (...ids) => Array.of(...ids.map(id => this[_tasks].get(id)).filter(Boolean));
}

// Promise-based async simulation for fetching tasks
const fetchTasksAsync = () =>
    new Promise((resolve) => {
        setTimeout(() => {
            resolve([
                { title: 'Learn ES6', completed: false },
                { title: 'Build a Todo App', completed: false }
            ]);
        }, 500);
    });

// WeakMap for private metadata
const taskMeta = new WeakMap();

// Example usage (can be moved to another module)
const todoList = new TodoList();

// Load initial tasks asynchronously
fetchTasksAsync().then(tasks => {
    tasks.forEach(({ title, completed }) => {
        const task = todoList.addTask(title);
        task.completed = completed;
        // Store metadata in WeakMap
        taskMeta.set(task, { created: new Date() });
    });

    // Add a new task
    const newTask = todoList.addTask('Write documentation');
    taskMeta.set(newTask, { created: new Date() });

    // Iterate using for...of
    for (const task of todoList) {
        const { id, title, completed } = task; // destructuring
        const meta = taskMeta.get(task);
        console.log(
            `Task #${id}: ${title} [${completed ? 'Done' : 'Pending'}] (Created: ${meta.created.toLocaleString()})`
        );
    }
});

// Export the TodoList class as a module
export { TodoList, Task };