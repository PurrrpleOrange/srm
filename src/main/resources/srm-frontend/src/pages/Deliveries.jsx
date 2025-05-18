import React, { useEffect, useState } from 'react';
import { getDeliveries } from '../api/delivery';

const Deliveries = () => {
  const [deliveries, setDeliveries] = useState([]);

  useEffect(() => {
    getDeliveries()
      .then(res => setDeliveries(res.data))
      .catch(() => alert('Ошибка загрузки поставок'));
  }, []);

  return (
    <div>
      <h2>Список поставок</h2>
      <table border="1" cellPadding="8" style={{ width: '100%', borderCollapse: 'collapse', fontSize: '14px' }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Дата</th>
            <th>Поставщик</th>
            <th>Статус</th>
            <th>Заявка</th>
          </tr>
        </thead>
        <tbody>
          {deliveries.map(delivery => (
            <tr key={delivery.id}>
              <td>{delivery.id}</td>
              <td>{delivery.date}</td>
              <td>{delivery.supplier?.name || '—'}</td>
              <td>{delivery.status}</td>
              <td>{delivery.supplyRequest?.id || '—'}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Deliveries;