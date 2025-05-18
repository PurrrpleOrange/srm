import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

console.log("✅ main.jsx загружен!");

const rootElement = document.getElementById('root');
if (!rootElement) {
  console.error("❌ Элемент #root не найден в index.html");
} else {
  const root = ReactDOM.createRoot(rootElement);
  root.render(
    <React.StrictMode>
      <App />
    </React.StrictMode>
  );
}
