import React, { useEffect, useState } from 'react';
import { getProducts, createProduct, deleteProduct } from '../api/product';

const Products = () => {
  const [products, setProducts] = useState([]);
  const [selectedId, setSelectedId] = useState(null);
  const [formVisible, setFormVisible] = useState(false);
  const [form, setForm] = useState({
    articleNumber: '',
    name: '',
    description: '',
    category: '',
    unit: ''
  });

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = () => {
    getProducts()
      .then(res => setProducts(res.data))
      .catch(() => alert('Ошибка загрузки товаров'));
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    createProduct(form)
      .then(() => {
        setFormVisible(false);
        setForm({ articleNumber: '', name: '', description: '', category: '', unit: '' });
        loadProducts();
      })
      .catch(() => alert('Ошибка добавления'));
  };

  const handleDelete = () => {
    if (!selectedId) return alert('Выберите товар');
    deleteProduct(selectedId)
      .then(() => {
        setSelectedId(null);
        loadProducts();
      })
      .catch(() => alert('Ошибка удаления'));
  };

  return (
    <div>
      <div style={{ marginBottom: '20px' }}>
        <button onClick={() => setFormVisible(true)} style={{ marginRight: '10px' }}>
          Добавить товар
        </button>
        <button onClick={handleDelete}>Удалить товар</button>
      </div>

      <table
        border="1"
        cellPadding="8"
        style={{
          width: '100%',
          borderCollapse: 'collapse',
          color: '#000',
          fontFamily: 'sans-serif',
          fontSize: '14px'
        }}
      >
        <thead>
          <tr>
            <th></th>
            <th>Артикул</th>
            <th>Название</th>
            <th>Описание</th>
            <th>Категория</th>
            <th>Ед. изм.</th>
          </tr>
        </thead>
        <tbody>
          {products.map(p => (
            <tr
              key={p.id}
              onClick={() => setSelectedId(p.id)}
              style={{
                backgroundColor: selectedId === p.id ? '#d0e7ff' : (products.indexOf(p) % 2 === 0 ? '#f9f9f9' : '#ffffff'),
                cursor: 'pointer'
              }}
            >
              <td><input type="radio" name="select" /></td>
              <td>{p.articleNumber}</td>
              <td>{p.name}</td>
              <td>{p.description}</td>
              <td>{p.category}</td>
              <td>{p.unit}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {formVisible && (
        <div style={{ marginTop: '30px' }}>
          <h3>Добавить новый товар</h3>
          <form onSubmit={handleSubmit}>
            <input name="articleNumber" placeholder="Артикул" value={form.articleNumber} onChange={handleChange} required /><br />
            <input name="name" placeholder="Название" value={form.name} onChange={handleChange} required /><br />
            <input name="description" placeholder="Описание" value={form.description} onChange={handleChange} /><br />
            <input name="category" placeholder="Категория" value={form.category} onChange={handleChange} /><br />
            <input name="unit" placeholder="Ед. измерения" value={form.unit} onChange={handleChange} /><br />
            <button type="submit">Сохранить</button>
          </form>
        </div>
      )}
    </div>
  );
};

export default Products;
