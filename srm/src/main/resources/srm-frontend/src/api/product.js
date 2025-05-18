import axios from 'axios';

const API_URL = 'http://localhost:8081/api/products';

export const getProducts = () => {
  return axios.get(API_URL);
};

export const createProduct = (data) => {
  return axios.post(API_URL, data);
};

export const deleteProduct = (id) => {
  return axios.delete(`${API_URL}/${id}`);
};
