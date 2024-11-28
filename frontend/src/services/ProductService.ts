import Api from "./Api";

export interface IProduct {
  name: string;
  description: string;
  price: number;
}

function getProducts() {
  return Api.getInstance().get("/api/products");
}

function createProduct(product: IProduct) {
  return Api.getInstance().post("/api/products", product);
}

function getProductById(productId: string) {
  return Api.getInstance().get(`/api/products/${productId}`);
}

function updateProduct(product: IProduct, productId: string) {
  return Api.getInstance().put(`/api/products/${productId}`, product);
}

function deleteProduct(productId: string) {
  return Api.getInstance().delete(`/api/products/${productId}`);
}

export default {
  getProducts,
  createProduct,
  getProductById,
  updateProduct,
  deleteProduct,
};
