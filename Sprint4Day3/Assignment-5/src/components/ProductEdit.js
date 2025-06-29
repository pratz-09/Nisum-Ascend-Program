import React, { useState } from 'react';

function ProductEdit() {
  const [name, setName] = useState('Phone');
  const [price, setPrice] = useState(10000);

  const handleUpdate = () => {
    alert(`Updated product: ${name} - ₹${price}`);
  };

  return (
    <div>
      <h2>Edit Product</h2>
      <input value={name} onChange={e => setName(e.target.value)} />
      <input type="number" value={price} onChange={e => setPrice(e.target.value)} />
      <button onClick={handleUpdate}>Update</button>
    </div>
  );
}

export default ProductEdit;
