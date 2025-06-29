import React from 'react';
import UserAuth from './components/UserAuth';
import ProductList from './components/ProductList';
import Cart from './components/Cart';

function App() {
  return (
    <div>
      <h1>E-Commerce App</h1>
      <UserAuth />
      <ProductList />
      <Cart />
    </div>
  );
}

export default App;
