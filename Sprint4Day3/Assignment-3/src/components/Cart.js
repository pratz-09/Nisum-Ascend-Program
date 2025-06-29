import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { selectCartItems, selectCartTotal, removeFromCart } from '../features/cartSlice';

function Cart() {
  const items = useSelector(selectCartItems);
  const total = useSelector(selectCartTotal);
  const dispatch = useDispatch();

  return (
    <div>
      <h2>Cart ({items.length} items)</h2>
      <ul>
        {items.map(item => (
          <li key={item.id}>
            {item.name} - ₹{item.price}
            <button onClick={() => dispatch(removeFromCart(item.id))}>Remove</button>
          </li>
        ))}
      </ul>
      <p>Total: ₹{total}</p>
    </div>
  );
}

export default Cart;
