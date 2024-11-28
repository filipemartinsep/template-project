import axios from "axios";

const API_BASE_URL = "http://localhost:8080";

class Api {
  instance;

  constructor() {
    this.instance = axios.create({
      baseURL: API_BASE_URL,
      withCredentials: true,
    });
  }

  getInstance() {
    return this.instance;
  }
}

export default new Api();
