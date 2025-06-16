import React, { useState } from 'react';
import { Sun, Moon } from 'lucide-react';

const ThemeToggle: React.FC = () => {
  const [isDark, setIsDark] = useState(false);

  const toggleTheme = () => {
    setIsDark(!isDark);
  };

  return (
    <div 
      className={`p-6 rounded-xl transition-all duration-300 ${
        isDark ? 'bg-gray-800 text-white' : 'bg-white text-gray-900'
      }`}
      style={{ border: '1px solid', borderColor: isDark ? '#374151' : '#e5e7eb' }}
    >
      <h3 className="text-lg font-semibold mb-4">Theme Toggle</h3>
      <button
        onClick={toggleTheme}
        className={`flex items-center gap-2 px-4 py-2 rounded-lg transition-all duration-200 ${
          isDark 
            ? 'bg-blue-600 hover:bg-blue-700 text-white' 
            : 'bg-blue-500 hover:bg-blue-600 text-white'
        }`}
      >
        {isDark ? <Sun size={18} /> : <Moon size={18} />}
        {isDark ? 'Light Mode' : 'Dark Mode'}
      </button>
      <p className="mt-3 text-sm opacity-75">
        Current theme: {isDark ? 'Dark' : 'Light'}
      </p>
    </div>
  );
};

export default ThemeToggle;