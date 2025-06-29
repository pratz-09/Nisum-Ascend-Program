import React, { useContext } from 'react';
import { UserContext } from './UserContext';

const Dashboard = () => {
  const { user, logout } = useContext(UserContext);

  return (
    <div>
      <h2>Dashboard</h2>
      <p>Welcome, {user.name}!</p>

      {user.role === 'admin' && (
        <div style={{ border: '1px solid green', padding: '10px', marginTop: '10px' }}>
          <h3>Admin Panel</h3>
          <button>Add Product</button>
        </div>
      )}

      <button onClick={logout} style={{ marginTop: '20px' }}>Logout</button>
    </div>
  );
};

export default Dashboard;
