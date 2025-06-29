import React, { createContext, useState } from 'react';

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState({});
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const loginAsAdmin = () => {
    setUser({ id: 1, name: 'Alice', role: 'admin' });
    setIsLoggedIn(true);
  };

  const loginAsUser = () => {
    setUser({ id: 2, name: 'Bob', role: 'user' });
    setIsLoggedIn(true);
  };

  const logout = () => {
    setUser({});
    setIsLoggedIn(false);
  };

  return (
    <UserContext.Provider value={{ user, isLoggedIn, loginAsAdmin, loginAsUser, logout }}>
      {children}
    </UserContext.Provider>
  );
};
