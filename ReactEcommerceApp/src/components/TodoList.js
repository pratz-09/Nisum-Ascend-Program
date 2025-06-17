import React, { useReducer } from 'react';

const initialState = [];

function reducer(state, action) {
  switch (action.type) {
    case 'ADD':
      return [...state, { id: Date.now(), text: action.text, done: false }];
    case 'DELETE':
      return state.filter(todo => todo.id !== action.id);
    case 'TOGGLE':
      return state.map(todo => todo.id === action.id ? { ...todo, done: !todo.done } : todo);
    default:
      return state;
  }
}

export default function TodoList() {
  const [todos, dispatch] = useReducer(reducer, []);
  const completed = todos.filter(t => t.done).length;
  const pending = todos.length - completed;

  return (
    <div style={{ padding: '20px' }}>
      <h2>Todo List</h2>
      <form onSubmit={(e) => {
        e.preventDefault();
        const text = e.target.elements.todo.value;
        if (text) dispatch({ type: 'ADD', text });
        e.target.reset();
      }}>
        <input name="todo" placeholder="Enter todo" />
        <button type="submit">Add</button>
      </form>
      <p>✅ {completed} Completed | 🕐 {pending} Pending</p>
      <ul>
        {todos.map(todo => (
          <li key={todo.id} style={{ marginTop: '10px' }}>
            <input
              type="checkbox"
              checked={todo.done}
              onChange={() => dispatch({ type: 'TOGGLE', id: todo.id })}
            />
            <span style={{ textDecoration: todo.done ? 'line-through' : 'none' }}>{todo.text}</span>
            <button onClick={() => dispatch({ type: 'DELETE', id: todo.id })}>❌</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
