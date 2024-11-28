import { useState, useEffect, FormEventHandler } from "react";
import ProductService, { IProduct } from "../services/ProductService";
import { useNavigate, useParams } from "react-router-dom";

const UpdateProduct = () => {
  const params = useParams();
  const id = params.id as string;
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState<number>();
  const navigate = useNavigate();

  useEffect(() => {
    ProductService.getProductById(id)
      .then((res) => {
        const product = res.data;
        setName(product.name);
        setDescription(product.description);
        setPrice(product.price);
      })
      .catch((error) => {
        if (error.response.status === 403) {
          navigate("/auth");
        }
      });
  }, [id, navigate]);

  const updateProduct: FormEventHandler = (e) => {
    e.preventDefault();
    const product = { name, description, price } as IProduct;
    ProductService.updateProduct(product, id).then(() => {
      navigate("/products");
    });
  };

  return (
    <div>
      <div className="container">
        <div className="row">
          <div className="card col-md-6 offset-md-3 offset-md-3">
            <h3 className="text-center">Update Product</h3>
            <div className="card-body">
              <form onSubmit={updateProduct}>
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
                    type="number"
                    required
                    placeholder="Price"
                    name="price"
                    className="form-control"
                    value={price}
                    onChange={(e) => setPrice(e.target.valueAsNumber)}
                  />
                </div>
                <button type="submit" className="btn btn-success">
                  Save
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UpdateProduct;
