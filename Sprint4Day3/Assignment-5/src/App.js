import React from 'react';
import ProductEdit from './components/ProductEdit';
import withAdminAccess from './hoc/withAdminAccess';

const ProductEditWithAccess = withAdminAccess(ProductEdit);

function App() {
  // Simulate user with 'admin' or 'user' role
  const user = { name: 'John', role: 'admin' }; // Change to 'user' to test

  return (
    <div>
      <h1>Product Management Panel</h1>
      <ProductEditWithAccess user={user} />
    </div>
  );
}

export default App;
