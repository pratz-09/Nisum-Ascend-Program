import React, { useContext } from 'react';
import { ThemeContext } from './ThemeContext';

const Content = () => {
  const { theme } = useContext(ThemeContext);

  const style = {
    padding: '20px',
    backgroundColor: theme === 'light' ? '#fafafa' : '#222',
    color: theme === 'light' ? '#000' : '#fff',
    minHeight: '200px'
  };

  return (
    <main style={style}>
      <h2>This is the main content area.</h2>
      <p>The theme is currently set to <strong>{theme}</strong>.</p>
    </main>
  );
};

export default Content;
