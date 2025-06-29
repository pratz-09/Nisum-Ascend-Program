import React, { useContext } from 'react';
import { UserContext } from './UserContext';

const LoginButton = () => {
  const { loginAsAdmin, loginAsUser } = useContext(UserContext);

  return (
    <div>
      <h3>Login as:</h3>
      <button onClick={loginAsAdmin}>Admin</button>
      <button onClick={loginAsUser} style={{ marginLeft: '10px' }}>User</button>
    </div>
  );
};

export default LoginButton;
