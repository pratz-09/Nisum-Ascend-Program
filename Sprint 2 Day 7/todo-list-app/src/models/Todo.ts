class Todo {
    id: number;
    title: string;
    completed: boolean;

    constructor(id: number, title: string, completed: boolean = false) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    toggleCompletion(): void {
        this.completed = !this.completed;
    }
}

export default Todo;