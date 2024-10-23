package DAO;

import model.user;

import java.util.List;

public interface userDAO {
    public List<user> getAllUsers();
    public user getUserById(int id);
    public user getUserByNameAndPassword(String name, String password);
    public void removeUser(int id);
}
