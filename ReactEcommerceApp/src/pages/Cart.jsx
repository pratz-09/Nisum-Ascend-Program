import React from 'react';
import { useCart } from '../contexts/CartContext';

export default function Cart() {
  const { cart, removeFromCart, clearCart } = useCart();

  const total = cart.reduce((sum, p) => sum + p.price, 0);

  return (
    <div style={{ padding: '20px' }}>
      <h2>Your Cart</h2>
      {cart.length === 0 ? <p>Cart is empty.</p> : (
        <>
          <ul>
            {cart.map(p => (
              <li key={p.id} style={{ marginBottom: '10px' }}>
                {p.name} - ₹{p.price}
                <button onClick={() => removeFromCart(p.id)}>Remove</button>
              </li>
            ))}
          </ul>
          <p><strong>Total:</strong> ₹{total}</p>
          <button onClick={clearCart}>Clear Cart</button>
        </>
      )}
    </div>
  );
}
