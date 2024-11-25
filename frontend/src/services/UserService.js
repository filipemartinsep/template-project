import axios from "axios";

const PRODUCT_API_BASE_URL = "http://localhost:8080/api/";

class UserService {
  instance;

  constructor() {
    this.instance = axios.create({
      baseURL: PRODUCT_API_BASE_URL,
      withCredentials: true,
    });
  }

  getProducts() {
    return this.instance.get("users");
  }

  createProduct(product) {
    return this.instance.post("users", product);
  }

  getProductById(id) {
    return this.instance.get(`users/${id}`);
  }

  updateProduct(user, id) {
    return this.instance.put(`users/${id}`, user);
  }

  deleteProduct(id) {
    return this.instance.delete(`users/${id}`);
  }
}

export default new UserService();
