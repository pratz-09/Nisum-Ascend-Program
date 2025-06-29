import React from 'react';
import { useDispatch } from 'react-redux';
import { addToCart } from '../features/cartSlice';

const products = [
  { id: 1, name: 'Phone', price: 9999 },
  { id: 2, name: 'Laptop', price: 49999 },
  { id: 3, name: 'Headphones', price: 1999 },
];

function ProductList() {
  const dispatch = useDispatch();

  return (
    <div>
      <h2>Products</h2>
      <ul>
        {products.map(prod => (
          <li key={prod.id}>
            {prod.name} - ₹{prod.price}
            <button onClick={() => dispatch(addToCart(prod))}>Add to Cart</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ProductList;
