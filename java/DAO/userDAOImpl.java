package DAO;

import Model.user;

import java.util.ArrayList;
import java.util.List;

public class userDAOImpl implements userDAO {

    List<user> users = new ArrayList<>();

    @Override
    public void addUser(user user) {
        users.add(user);
    }

    @Override
    public List<user> getAllUsers() {
        return users;
    }

    @Override
    public user getUserById(int id) {
        for (user user : users){
            if (user.getId() == id) return user;
        }
        return null;
    }

    @Override
    public user getUserByNameAndPassword(String name, String password) {
        for (user user: users){
            if(user.getName().equals(name) && user.getPassword().equals(password)) return user;
        }
        return null;
    }

    @Override
    public user getUserByName(String name) {
        for (user user: users){
            if(user.getName().equals(name)) return user;
        }
        return null;
    }

    @Override
    public void removeUser(int id) {
        user user = getUserById(id);
        users.remove(user);
    }
}
