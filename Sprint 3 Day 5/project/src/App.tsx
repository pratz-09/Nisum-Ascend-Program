import React, { useState } from 'react';
import Layout from './components/Layout';
import Navigation from './components/Navigation';
import Home from './pages/Home';
import About from './pages/About';
import Dashboard from './components/Dashboard';

function App() {
  const [currentPage, setCurrentPage] = useState('home');

  const renderCurrentPage = () => {
    switch (currentPage) {
      case 'home':
        return <Home />;
      case 'about':
        return <About />;
      case 'dashboard':
        return <Dashboard />;
      default:
        return <Home />;
    }
  };

  return (
    <Layout>
      <Navigation />
      
      <div className="py-8 space-y-8">
        <nav className="flex justify-center">
          <div className="bg-white rounded-xl p-2 shadow-sm border border-gray-200">
            <div className="flex gap-2">
              <button
                onClick={() => setCurrentPage('home')}
                className={`px-4 py-2 rounded-lg font-medium transition-all ${
                  currentPage === 'home' 
                    ? 'bg-blue-500 text-white shadow-md' 
                    : 'text-gray-600 hover:bg-gray-100'
                }`}
              >
                Home
              </button>
              <button
                onClick={() => setCurrentPage('about')}
                className={`px-4 py-2 rounded-lg font-medium transition-all ${
                  currentPage === 'about' 
                    ? 'bg-blue-500 text-white shadow-md' 
                    : 'text-gray-600 hover:bg-gray-100'
                }`}
              >
                About
              </button>
              <button
                onClick={() => setCurrentPage('dashboard')}
                className={`px-4 py-2 rounded-lg font-medium transition-all ${
                  currentPage === 'dashboard' 
                    ? 'bg-blue-500 text-white shadow-md' 
                    : 'text-gray-600 hover:bg-gray-100'
                }`}
              >
                Dashboard
              </button>
            </div>
          </div>
        </nav>

        {renderCurrentPage()}
      </div>
    </Layout>
  );
}

export default App;