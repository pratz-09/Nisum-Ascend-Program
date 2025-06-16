import React from 'react';
import TodoList from '../components/TodoList';
import StatusBadge from '../components/StatusBadge';

const About: React.FC = () => {
  const sampleTodos = [
    { id: 1, text: "Learn React hooks", completed: true },
    { id: 2, text: "Build component library", completed: false },
    { id: 3, text: "Write unit tests", completed: false },
    { id: 4, text: "Deploy to production", completed: true }
  ];

  return (
    <div className="space-y-8">
      <section>
        <h2 className="text-2xl font-bold text-gray-800 mb-4">About This Project</h2>
        <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-200">
          <p className="text-gray-600 leading-relaxed mb-4">
            This project demonstrates a comprehensive set of React components built with TypeScript, 
            Tailwind CSS, and modern React patterns. Each component showcases different aspects of 
            React development including state management, props handling, and styling approaches.
          </p>
          
          <div className="flex flex-wrap gap-3 mb-4">
            <StatusBadge status="success" label="Components Ready" />
            <StatusBadge status="warning" label="Testing Pending" />
            <StatusBadge status="error" label="Docs Missing" />
          </div>
        </div>
      </section>

      <section>
        <TodoList todos={sampleTodos} />
      </section>
    </div>
  );
};

export default About;