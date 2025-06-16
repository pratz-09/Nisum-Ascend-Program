import React from 'react';
import { CheckCircle2 } from 'lucide-react';

interface Todo {
  id: number;
  text: string;
  completed: boolean;
}

interface TodoListProps {
  todos: Todo[];
}

const TodoList: React.FC<TodoListProps> = ({ todos }) => {
  return (
    <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-200">
      <h3 className="text-xl font-semibold text-gray-800 mb-4 flex items-center gap-2">
        <CheckCircle2 className="text-green-500" size={20} />
        Todo List
      </h3>
      <ul className="space-y-2">
        {todos.map((todo) => (
          <li 
            key={todo.id} 
            className={`p-3 rounded-lg border transition-colors ${
              todo.completed 
                ? 'bg-green-50 border-green-200 text-green-800 line-through' 
                : 'bg-gray-50 border-gray-200 text-gray-700'
            }`}
          >
            {todo.text}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TodoList;