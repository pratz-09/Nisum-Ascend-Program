export type TodoStatus = 'pending' | 'completed' | 'in-progress';

export type TodoId = number;

export interface Todo {
    id: TodoId;
    title: string;
    completed: boolean;
    createdAt?: Date;
    updatedAt?: Date;
}

export type TodoList = Todo[];