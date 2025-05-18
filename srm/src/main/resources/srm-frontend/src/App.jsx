import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Suppliers from './pages/Suppliers';
import Products from './pages/Products';  
import Dashboard from './pages/Dashboard';
import Deliveries from './pages/Deliveries';

function App() {
  return (
    <Router>
      <div style={{ padding: '20px' }}>
        <nav style={{ marginBottom: '20px' }}>
          <Link to="/" style={{ marginRight: '15px' }}>Главная</Link>
          <Link to="/suppliers" style={{ marginRight: '15px' }}>Поставщики</Link>
          <Link to="/products" style={{ marginRight: '15px' }}>Товары</Link>
          <Link to="/deliveries" style={{ marginRight: '15px' }}>Поставки</Link>
        </nav>

        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/suppliers" element={<Suppliers />} />
          <Route path="/products" element={<Products />} />
          <Route path="/deliveries" element={<Deliveries />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;