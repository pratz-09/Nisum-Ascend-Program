import React from 'react';
import { ThemeProvider } from './ThemeContext';
import Header from './Header';
import Content from './Content';

const App = () => {
  return (
    <ThemeProvider>
      <Header />
      <Content />
    </ThemeProvider>
  );
};

export default App;
