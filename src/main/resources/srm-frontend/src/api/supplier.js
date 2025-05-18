import axios from 'axios';

const API_URL = 'http://localhost:8081/api/suppliers';

export const getSuppliers = () => {
  return axios.get(API_URL);
};

export const createSupplier = (data) => {
  return axios.post(API_URL, data);
};

export const deleteSupplier = (id) => {
  return axios.delete(`${API_URL}/${id}`);
};

export const updateSupplier = (id, data) => {
  return axios.put(`http://localhost:8081/api/suppliers/${id}`, data);
};
