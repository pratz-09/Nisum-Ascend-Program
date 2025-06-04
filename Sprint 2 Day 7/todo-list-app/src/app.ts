// Basic Types, Tuple, Enum, Literal Types, Nullable Types, Optional Properties
enum TodoStatus {
  Pending = "pending",
  Completed = "completed"
}

type TodoId = number | string; // Union Type, Type Alias

type Priority = "low" | "medium" | "high"; // Literal Types

interface ITodo {
  id: TodoId;
  title: string;
  completed: boolean;
  status: TodoStatus;
  priority?: Priority; // Optional Property
  description?: string | null; // Nullable Type
}

// Type Guard
function isTodo(obj: any): obj is ITodo {
  return (
    typeof obj === "object" &&
    "id" in obj &&
    "title" in obj &&
    "completed" in obj &&
    "status" in obj
  );
}

// Generics, Array, Tuple
class TodoList<T extends ITodo> {
  private todos: T[] = [];

  add(todo: T): void {
    if (isTodo(todo)) {
      this.todos.push(todo);
    }
  }

  getAll(): T[] {
    return this.todos;
  }

  findById(id: TodoId): T | undefined {
    return this.todos.find(todo => todo.id === id);
  }

  markCompleted(id: TodoId): void {
    const todo = this.findById(id);
    if (todo) {
      todo.completed = true;
      todo.status = TodoStatus.Completed;
    }
  }
}

// Typecasting function
function toTodo(obj: any): ITodo {
  return obj as ITodo;
}

// Discriminated Union
type TodoAction =
  | { type: "add"; payload: ITodo }
  | { type: "complete"; payload: TodoId }
  | { type: "list" };

// Conditional Type
type ActionPayload<T> = T extends { payload: infer P } ? P : never;

// Mapped Type
type TodoPreview = {
  [K in keyof ITodo]?: ITodo[K];
};

// Usage
const todoList = new TodoList<ITodo>();

const todo1: ITodo = {
  id: 1,
  title: "Learn TypeScript",
  completed: false,
  status: TodoStatus.Pending,
  priority: "high",
  description: null
};

const todo2 = toTodo({
  id: 2,
  title: "Build a Todo App",
  completed: false,
  status: TodoStatus.Pending
});

todoList.add(todo1);
todoList.add(todo2);

function handleAction(action: TodoAction) {
  switch (action.type) {
    case "add":
      todoList.add(action.payload);
      break;
    case "complete":
      todoList.markCompleted(action.payload);
      break;
    case "list":
      console.log(todoList.getAll());
      break;
    default:
      // never type
      const _exhaustive: never = action;
      throw new Error(`Unhandled action: ${_exhaustive}`);
  }
}

// Demo
handleAction({ type: "list" });
handleAction({ type: "complete", payload: 1 });
handleAction({ type: "list" });