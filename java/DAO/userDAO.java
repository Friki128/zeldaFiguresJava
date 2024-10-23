package DAO;

import Model.user;

import java.util.List;

public interface userDAO {
    public void addUser(user user);
    public List<user> getAllUsers();
    public user getUserById(int id);
    public user getUserByNameAndPassword(String name, String password);
    public user getUserByName(String name);
    public void removeUser(int id);
}
