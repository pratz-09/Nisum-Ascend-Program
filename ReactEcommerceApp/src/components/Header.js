import React from 'react';
import { Link } from 'react-router-dom';
import { useCart } from '../contexts/CartContext';

export default function Header() {
  const { cart } = useCart();
  return (
    <nav style={{ padding: '10px', background: '#eee', display: 'flex', gap: '10px' }}>
      <Link to="/">Home</Link>
      <Link to="/cart">Cart ({cart.length})</Link>
      <Link to="/wishlist">Wishlist</Link>
      <Link to="/todo">Todo</Link>
    </nav>
  );
}
