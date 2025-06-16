import React from 'react';
import Greeting from '../components/Greeting';
import Counter from '../components/Counter';
import ProductCard from '../components/ProductCard';
import ThemeToggle from '../components/ThemeToggle';

const Home: React.FC = () => {
  return (
    <div className="space-y-8">
      <section>
        <h2 className="text-2xl font-bold text-gray-800 mb-4">Welcome Home</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <Greeting name="React Developer" />
          <ThemeToggle />
        </div>
      </section>

      <section>
        <h2 className="text-2xl font-bold text-gray-800 mb-4">Interactive Counter</h2>
        <Counter />
      </section>

      <section>
        <h2 className="text-2xl font-bold text-gray-800 mb-4">Featured Products</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          <ProductCard 
            title="iPhone 15"
            price={1099}
            description="Latest model with improved battery life."
          />
          <ProductCard 
            title="MacBook Pro"
            price={2299}
            description="Powerful laptop for professional use."
          />
          <ProductCard />
        </div>
      </section>
    </div>
  );
};

export default Home;