import React from 'react';
import { useTheme } from '../contexts/ThemeContext';

export default function ThemeToggle() {
  const { dark, toggleTheme } = useTheme();
  return (
    <button onClick={toggleTheme} style={{ margin: '10px' }}>
      Switch to {dark ? 'Light' : 'Dark'} Mode
    </button>
  );
}
