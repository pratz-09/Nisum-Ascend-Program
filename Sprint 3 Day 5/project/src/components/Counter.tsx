import React, { useState, useEffect } from 'react';
import '../styles/Counter.css';

const Counter: React.FC = () => {
  const [count, setCount] = useState(0);

  useEffect(() => {
    console.log(`Count changed to: ${count}`);
  }, [count]);

  const handleIncrement = () => {
    setCount(prevCount => prevCount + 1);
  };

  const handleDecrement = () => {
    setCount(prevCount => prevCount - 1);
  };

  const handleReset = () => {
    setCount(0);
  };

  return (
    <div className="counter-container">
      <h1 className="counter-title">Count: {count}</h1>
      <div className="counter-buttons">
        <button 
          className="counter-btn increment-btn" 
          onClick={handleIncrement}
        >
          Increment
        </button>
        <button 
          className="counter-btn decrement-btn" 
          onClick={handleDecrement}
          disabled={count === 0}
        >
          Decrement
        </button>
        <button 
          className="counter-btn reset-btn" 
          onClick={handleReset}
        >
          Reset
        </button>
      </div>
    </div>
  );
};

export default Counter;