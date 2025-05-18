import React, { useEffect, useState, useRef } from 'react';
import { FaStar, FaRegStar, FaStarHalfAlt } from 'react-icons/fa';
import {
  getSuppliers,
  createSupplier,
  deleteSupplier,
  updateSupplier
} from '../api/supplier';

const Suppliers = () => {
  const [suppliers, setSuppliers] = useState([]);
  const [selectedSupplier, setSelectedSupplier] = useState(null);
  const [formVisible, setFormVisible] = useState(false);
  const [formMode, setFormMode] = useState('create');

  const formRef = useRef(null);

  const [form, setForm] = useState({
    name: '',
    contactEmail: '',
    phoneNumber: '',
    category: '',
    taxId: '',
    address: '',
    active: true
  });

  useEffect(() => {
    loadSuppliers();
  }, []);

  const loadSuppliers = () => {
    getSuppliers()
      .then(res => setSuppliers(res.data))
      .catch(() => alert('Ошибка загрузки поставщиков'));
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm({ ...form, [name]: type === 'checkbox' ? checked : value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (formMode === 'create') {
      createSupplier(form)
        .then(() => {
          resetForm();
          loadSuppliers();
        })
        .catch(() => alert('Ошибка добавления'));
    } else if (formMode === 'edit' && selectedSupplier) {
      updateSupplier(selectedSupplier.id, form)
        .then(() => {
          resetForm();
          loadSuppliers();
        })
        .catch(() => alert('Ошибка редактирования'));
    }
  };

  const handleDelete = () => {
    if (!selectedSupplier) return alert('Выберите поставщика');

    const confirmed = window.confirm(`Удалить поставщика "${selectedSupplier.name}"?`);
    if (!confirmed) return;

    deleteSupplier(selectedSupplier.id)
      .then(() => {
        setSelectedSupplier(null);
        loadSuppliers();
      })
      .catch(() => alert('Ошибка удаления'));
  };

  const handleEdit = () => {
    if (!selectedSupplier) return alert('Выберите поставщика');
    setForm({ ...selectedSupplier });
    setFormMode('edit');
    setFormVisible(true);
  };

  const resetForm = () => {
    setFormMode('create');
    setFormVisible(false);
    setForm({
      name: '',
      contactEmail: '',
      phoneNumber: '',
      category: '',
      taxId: '',
      address: '',
      active: true
    });
  };

  const renderStars = (rating) => {
    const stars = [];
    for (let i = 1; i <= 5; i++) {
      if (rating >= i) {
        stars.push(<FaStar key={i} color="gold" />);
      } else if (rating >= i - 0.5) {
        stars.push(<FaStarHalfAlt key={i} color="gold" />);
      } else {
        stars.push(<FaRegStar key={i} color="gold" />);
      }
    }
    return <span>{stars}</span>;
  };

  return (
    <div>
      <div style={{ marginBottom: '20px' }}>
        <button onClick={() => {
          resetForm();
          setFormVisible(true);
        }} style={{ marginRight: '10px' }}>
          Добавить поставщика
        </button>
        <button onClick={handleEdit} style={{ marginRight: '10px' }}>
          Редактировать
        </button>
        <button onClick={handleDelete}>Удалить</button>
      </div>

      <table
        border="1"
        cellPadding="8"
        style={{ width: '100%', borderCollapse: 'collapse', color: '#000', fontSize: '14px' }}
      >
        <thead>
          <tr>
            <th>Название</th>
            <th>Email</th>
            <th>Телефон</th>
            <th>Категория</th>
          </tr>
        </thead>
        <tbody>
          {suppliers.map(s => (
            <tr
              key={s.id}
              onClick={() => setSelectedSupplier(s)}
              style={{
                backgroundColor: selectedSupplier?.id === s.id ? '#d0e7ff' : (suppliers.indexOf(s) % 2 === 0 ? '#f9f9f9' : '#ffffff'),
                cursor: 'pointer'
              }}
            >
              <td>{s.name}</td>
              <td>{s.contactEmail || '—'}</td>
              <td>{s.phoneNumber}</td>
              <td>{s.category}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {selectedSupplier && (
        <div style={{ marginTop: '30px', background: '#f2f2f2', padding: '20px', borderRadius: '8px' }}>
          <button onClick={() => setSelectedSupplier(null)} style={{ float: 'right' }}>Скрыть</button>
          <h3>Детали поставщика</h3>
          <p><strong>Название:</strong> {selectedSupplier.name}</p>
          <p><strong>Email:</strong> {selectedSupplier.contactEmail}</p>
          <p><strong>Телефон:</strong> {selectedSupplier.phoneNumber}</p>
          <p><strong>Категория:</strong> {selectedSupplier.category}</p>
          <p><strong>ИНН:</strong> {selectedSupplier.taxId}</p>
          <p><strong>Адрес:</strong> {selectedSupplier.address}</p>
          <p><strong>Рейтинг:</strong> {renderStars(selectedSupplier.rating)} ({selectedSupplier.rating})</p>
          <p><strong>Активен:</strong> {selectedSupplier.active ? 'Да' : 'Нет'}</p>
        </div>
      )}

      {formVisible && (
        <div ref={formRef} style={{ marginTop: '30px' }}>
          <h3>{formMode === 'edit' ? 'Редактировать поставщика' : 'Добавить нового поставщика'}</h3>
          <form onSubmit={handleSubmit}>
            <input name="name" placeholder="Название" value={form.name} onChange={handleChange} required /><br />
            <input name="contactEmail" placeholder="Email" value={form.contactEmail} onChange={handleChange} required /><br />
            <input name="phoneNumber" placeholder="Телефон" value={form.phoneNumber} onChange={handleChange} /><br />
            <input name="category" placeholder="Категория" value={form.category} onChange={handleChange} /><br />
            <input name="taxId" placeholder="ИНН" value={form.taxId} onChange={handleChange} /><br />
            <input name="address" placeholder="Адрес" value={form.address} onChange={handleChange} /><br />
            <label>
              <input type="checkbox" name="active" checked={form.active} onChange={handleChange} />
              Активен
            </label><br />
            <button type="submit">{formMode === 'edit' ? 'Сохранить изменения' : 'Сохранить'}</button>
            <button type="button" onClick={resetForm} style={{ marginLeft: '10px' }}>Отмена</button>
          </form>
        </div>
      )}
    </div>
  );
};

export default Suppliers;