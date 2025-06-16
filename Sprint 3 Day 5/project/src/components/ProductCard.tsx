import React from 'react';
import '../styles/ProductCard.css';

interface ProductCardProps {
  title?: string;
  price?: number;
  description?: string;
}

const ProductCard: React.FC<ProductCardProps> = ({ 
  title = "Sample Product", 
  price = 99, 
  description = "No description available." 
}) => {
  return (
    <div 
      className="product-card"
      style={{ border: '1px solid #e2e8f0', borderRadius: '12px' }}
    >
      <h2 className="product-title">{title}</h2>
      <p className="product-price">Price: ${price}</p>
      <p className="product-description">{description}</p>
    </div>
  );
};

export default ProductCard;