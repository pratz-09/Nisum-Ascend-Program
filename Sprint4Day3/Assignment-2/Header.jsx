import React, { useContext } from 'react';
import { ThemeContext } from './ThemeContext';

const Header = () => {
  const { theme, toggleTheme } = useContext(ThemeContext);

  const style = {
    padding: '10px',
    backgroundColor: theme === 'light' ? '#eee' : '#333',
    color: theme === 'light' ? '#000' : '#fff',
    display: 'flex',
    justifyContent: 'space-between'
  };

  return (
    <header style={style}>
      <h1>Theme Switcher App</h1>
      <button onClick={toggleTheme}>
        Switch to {theme === 'light' ? 'Dark' : 'Light'} Mode
      </button>
    </header>
  );
};

export default Header;
