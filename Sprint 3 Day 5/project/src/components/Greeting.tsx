import React from 'react';

interface GreetingProps {
  name: string;
}

const Greeting: React.FC<GreetingProps> = ({ name }) => {
  return (
    <div className="bg-gradient-to-r from-blue-50 to-indigo-50 p-6 rounded-xl border border-blue-100">
      <h2 className="text-2xl font-semibold text-gray-800 text-center">
        Hello, {name}!
      </h2>
    </div>
  );
};

export default Greeting;