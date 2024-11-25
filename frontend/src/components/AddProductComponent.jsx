import { useState } from "react";
import { useNavigate } from "react-router-dom";
import ProductService from "../services/ProductService";
import "bootstrap/dist/css/bootstrap.min.css";

const AddProductComponent = () => {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const navigate = useNavigate();

  const saveProduct = (e) => {
    e.preventDefault();
    const product = { name, description, price };
    ProductService.createProduct(product).then(() => {
      navigate("/products");
    });
  };

  const handleCancelClick = () => {
    navigate("/products");
  };

  return (
    <div>
      <div className="container">
        <div className="row">
          <div className="card col-md-6 offset-md-3 offset-md-3">
            <h3 className="text-center">Add Product</h3>
            <div className="card-body">
              <form className="">
                <div className="form-group">
                  <label> Product Name: </label>
                  <input
                    placeholder="Name"
                    name="name"
                    className="form-control"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label> Product Description: </label>
                  <input
                    placeholder="Description"
                    name="description"
                    className="form-control"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label> Product Price: </label>
                  <input
                    placeholder="Price"
                    name="price"
                    className="form-control"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                  />
                </div>
                <button className="btn btn-success" onClick={saveProduct}>
                  Save
                </button>
                <button className="btn btn-success" onClick={handleCancelClick}>
                  Cancel
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddProductComponent;
