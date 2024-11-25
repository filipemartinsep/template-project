import Api from "./Api";

function getProducts() {
  return Api.getInstance().get("/api/products");
}

function createProduct(product) {
  return Api.getInstance().post("/api/products", product);
}

function getProductById(productId) {
  return Api.getInstance().get(`/api/products/${productId}`);
}

function updateProduct(product, productId) {
  return Api.getInstance().put(`/api/products/${productId}`, product);
}

function deleteProduct(productId) {
  return Api.getInstance().delete(`/api/products/${productId}`);
}

export default {
  getProducts,
  createProduct,
  getProductById,
  updateProduct,
  deleteProduct,
};
