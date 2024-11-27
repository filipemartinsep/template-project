import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthenticationService from "../services/AuthenticationService";
import "bootstrap/dist/css/bootstrap.min.css";

const LoginComponent = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleAuthentication = (e) => {
    e.preventDefault();
    const user = { username, password };
    AuthenticationService.login(user).then(() => {
      navigate("/products");
    });
  };

  return (
    <div>
      <div className="container">
        <div className="row">
          <div className="card col-md-6 offset-md-3 offset-md-3">
            <h3 className="text-center">Login</h3>
            <div className="card-body">
              <form>
                <div className="form-group">
                  <label>Username: </label>
                  <input
                    placeholder="Username"
                    name="Username"
                    className="form-control"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                  />
                </div>
                <div className="form-group">
                  <label> Password: </label>
                  <input
                    placeholder="Password"
                    name="Password"
                    className="form-control"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
                <button
                  className="btn btn-success"
                  onClick={handleAuthentication}
                >
                  login
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginComponent;
