package DAO;

import Model.user;

import java.util.List;

public interface userDAO {
    void addUser(user user);
    List<user> getAllUsers();
    user getUserById(int id);
    user getUserByNameAndPassword(String name, String password);
    user getUserByName(String name);
    void removeUser(int id);
}
