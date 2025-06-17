import React, { useEffect, useState, useRef } from 'react';
import { Link } from 'react-router-dom';
import productsData from '../data/products.json';

export default function Home() {
  const [products, setProducts] = useState([]);
  const [filtered, setFiltered] = useState([]);
  const [search, setSearch] = useState('');
  const [category, setCategory] = useState('all');
  const [minPrice, setMinPrice] = useState(0);
  const [maxPrice, setMaxPrice] = useState(1000);
  const searchRef = useRef();

  useEffect(() => {
    setProducts(productsData);
    setFiltered(productsData);
  }, []);

  useEffect(() => {
    const delayDebounce = setTimeout(() => {
      filterProducts();
    }, 400);
    return () => clearTimeout(delayDebounce);
  }, [search, category, minPrice, maxPrice]);

  const filterProducts = () => {
    let temp = [...products];
    if (category !== 'all') temp = temp.filter(p => p.category === category);
    temp = temp.filter(p => p.name.toLowerCase().includes(search.toLowerCase()));
    temp = temp.filter(p => p.price >= minPrice && p.price <= maxPrice);
    setFiltered(temp);
  };

  const categories = [...new Set(products.map(p => p.category))];

  return (
    <div style={{ padding: '20px' }}>
      <h2>Product List</h2>
      <div>
        <input
          ref={searchRef}
          type="text"
          value={search}
          onChange={e => setSearch(e.target.value)}
          placeholder="Search by name"
        />
        <select value={category} onChange={e => setCategory(e.target.value)}>
          <option value="all">All Categories</option>
          {categories.map(cat => <option key={cat} value={cat}>{cat}</option>)}
        </select>
        <input
          type="number"
          placeholder="Min Price"
          value={minPrice}
          onChange={e => setMinPrice(Number(e.target.value))}
        />
        <input
          type="number"
          placeholder="Max Price"
          value={maxPrice}
          onChange={e => setMaxPrice(Number(e.target.value))}
        />
      </div>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: '20px', marginTop: '20px' }}>
        {filtered.map(p => (
          <div key={p.id} style={{ border: '1px solid #ccc', padding: '10px' }}>
            <Link to={`/products/${p.id}`}>
              <img src={p.image} alt={p.name} style={{ width: '100%' }} />
              <h4>{p.name}</h4>
              <p>₹{p.price}</p>
              <p>{p.category}</p>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
}