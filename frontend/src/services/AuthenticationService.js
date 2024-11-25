import Api from "./Api";

function login(user) {
  return Api.getInstance().post("auth/login", user);
}

export default { login };
