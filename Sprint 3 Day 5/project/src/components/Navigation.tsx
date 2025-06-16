import React, { useState } from 'react';
import { Home, Info, Settings, Phone } from 'lucide-react';
import '../styles/Navigation.css';

interface NavItem {
  id: string;
  label: string;
  icon: React.ReactNode;
}

const Navigation: React.FC = () => {
  const [activeTab, setActiveTab] = useState('home');

  const navItems: NavItem[] = [
    { id: 'home', label: 'Home', icon: <Home size={18} /> },
    { id: 'about', label: 'About', icon: <Info size={18} /> },
    { id: 'services', label: 'Services', icon: <Settings size={18} /> },
    { id: 'contact', label: 'Contact', icon: <Phone size={18} /> }
  ];

  return (
    <nav className="navigation">
      <div className="nav-brand">
        <h2>Component Demo</h2>
      </div>
      
      <div className="nav-items">
        {navItems.map((item) => (
          <NavItem
            key={item.id}
            id={item.id}
            label={item.label}
            icon={item.icon}
            isActive={activeTab === item.id}
            onClick={() => setActiveTab(item.id)}
          />
        ))}
      </div>
    </nav>
  );
};

interface NavItemProps {
  id: string;
  label: string;
  icon: React.ReactNode;
  isActive: boolean;
  onClick: () => void;
}

const NavItem: React.FC<NavItemProps> = ({ label, icon, isActive, onClick }) => {
  return (
    <button
      className={`nav-item ${isActive ? 'active' : ''}`}
      onClick={onClick}
    >
      {icon}
      <span>{label}</span>
    </button>
  );
};

export default Navigation;