import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { login, logout, selectUser } from '../features/userSlice';

function UserAuth() {
  const dispatch = useDispatch();
  const user = useSelector(selectUser);

  const handleLogin = () => {
    dispatch(login({ name: 'John Doe', email: 'john@example.com' }));
  };

  const handleLogout = () => {
    dispatch(logout());
  };

  return (
    <div>
      {user ? (
        <>
          <p>Welcome, {user.name}</p>
          <button onClick={handleLogout}>Logout</button>
        </>
      ) : (
        <button onClick={handleLogin}>Login</button>
      )}
    </div>
  );
}

export default UserAuth;
