import React, { ReactNode } from 'react';
import { Heart } from 'lucide-react';
import '../styles/Layout.css';

interface LayoutProps {
  children: ReactNode;
}

const Layout: React.FC<LayoutProps> = ({ children }) => {
  return (
    <div className="layout">
      <header className="layout-header">
        <div className="header-content">
          <h1>React Components Assignment</h1>
          <p>A comprehensive demonstration of React component patterns</p>
        </div>
      </header>
      
      <main className="layout-main">
        {children}
      </main>
      
      <footer className="layout-footer">
        <div className="footer-content">
          <p>Made with <Heart size={16} className="heart-icon" /> using React & TypeScript</p>
          <p>&copy; 2024 React Components Demo. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
};

export default Layout;