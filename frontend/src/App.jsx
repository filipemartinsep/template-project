import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import ProductListComponent from "./pages/ProductList";
import AddProductComponent from "./pages/AddProduct";
import UpdateProductComponent from "./pages/UpdateProduct";
import LoginComponent from "./pages/Login";

const App = () => {
  return (
    <Router>
      <div className="container">
        <Routes>
          <Route path="/auth" element={<LoginComponent />} />
          <Route path="/" element={<ProductListComponent />} />
          <Route path="/products" element={<ProductListComponent />} />
          <Route path="/add-product" element={<AddProductComponent />} />
          <Route
            path="/update-product/:id"
            element={<UpdateProductComponent />}
          />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
