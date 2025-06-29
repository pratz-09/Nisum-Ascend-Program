import React, { useContext } from 'react';
import { UserProvider, UserContext } from './UserContext';
import Dashboard from './Dashboard';
import LoginButton from './LoginButton';

const App = () => {
  return (
    <UserProvider>
      <MainApp />
    </UserProvider>
  );
};

const MainApp = () => {
  const { user, isLoggedIn } = useContext(UserContext);

  return (
    <div>
      <h1>Welcome to User Dashboard</h1>
      {!isLoggedIn && <LoginButton />}
      {isLoggedIn && (
        <>
          <p>Logged in as: {user.name} ({user.role})</p>
          <Dashboard />
        </>
      )}
    </div>
  );
};

export default App;
