import React from 'react';
import useWishlist from '../hooks/useWishlist';

export default function Wishlist() {
  const { wishlist, toggleWishlist } = useWishlist();

  return (
    <div style={{ padding: '20px' }}>
      <h2>Wishlist</h2>
      {wishlist.length === 0 ? <p>No items in wishlist.</p> : (
        <ul>
          {wishlist.map(p => (
            <li key={p.id} style={{ marginBottom: '10px' }}>
              ❤️ {p.name} - ₹{p.price}
              <button onClick={() => toggleWishlist(p)}>Remove</button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
