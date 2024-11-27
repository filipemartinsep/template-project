import { useEffect, useState } from "react";
import ProductService from "../services/ProductService";
import { Link, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

const ProductListComponent = () => {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    ProductService.getProducts()
      .then((res) => {
        setProducts(res.data);
      })
      .catch((error) => {
        if (error.response.status === 403) {
          navigate("/auth");
        }
      });
  }, [navigate]);

  const handleDeleteClick = (id) =>
    ProductService.deleteProduct(id).then(() =>
      setProducts(products.filter((p) => p.id !== id))
    );

  return (
    <div>
      <h2 className="text-center">Product List</h2>
      <div className="row">
        <Link to="/add-product" className="btn btn-primary">
          Add Product
        </Link>
      </div>
      <div className="row">
        <table className="table table-striped table-bordered">
          <thead>
            <tr>
              <th>Name</th>
              <th>Description</th>
              <th>Price</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {products.map((product) => (
              <tr key={product.id}>
                <td>{product.name}</td>
                <td>{product.description}</td>
                <td>{product.price}</td>
                <td className="d-flex gap-2">
                  <Link
                    to={`/update-product/${product.id}`}
                    className="btn btn-info"
                  >
                    Update
                  </Link>
                  <button
                    className="btn btn-danger"
                    onClick={() => handleDeleteClick(product.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ProductListComponent;
