import axios from 'axios';

const API_URL = 'http://localhost:8081/api/deliveries';

export const getDeliveries = () => {
  return axios.get(API_URL);
};