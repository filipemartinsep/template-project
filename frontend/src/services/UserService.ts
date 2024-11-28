import Api from "./Api";

export interface IUser {
  username: string;
  password: string;
  email: string;
  role: string;
}

function getUsers() {
  return Api.getInstance().get("/api/users");
}

function createUser(user: IUser) {
  return Api.getInstance().post("/api/users", user);
}

function getUserById(id: string) {
  return Api.getInstance().get(`/api/users/${id}`);
}

function updateUser(user: IUser, id: string) {
  return Api.getInstance().put(`/api/users/${id}`, user);
}

function deleteUser(id: string) {
  return Api.getInstance().delete(`/api/users/${id}`);
}

export default {
  getUsers,
  getUserById,
  updateUser,
  deleteUser,
};
