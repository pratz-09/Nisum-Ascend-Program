import React from 'react';

const withAdminAccess = (Component) => {
  return function WrappedComponent(props) {
    if (props.user?.role !== 'admin') {
      return <h3>Access Denied</h3>;
    }
    return <Component {...props} />;
  };
};

export default withAdminAccess;
