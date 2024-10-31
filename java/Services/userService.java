package Services;

import DAO.userDAOImpl;
import Exceptions.*;
import Model.user;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.List;

public class userService {
    userDAOImpl userDAO = new userDAOImpl();
    static int nextId = 0;
    public user getUserByName(String name){
        return userDAO.getUserByName(name);
    }
    public user login(String name, String password){
        String hashedPassword = hashPassword(password);
        return userDAO.getUserByNameAndPassword(name, hashedPassword);
    }
    public void register(String name, String password) throws passwordLengthException, nameAlreadyInUseException, emtyNameException {
        if (password.length() < 6) throw new passwordLengthException();
        String fixedName = name.replace(" ", "");
        if(fixedName.isEmpty()) throw new emtyNameException();
        if (getUserByName(fixedName) != null) throw new nameAlreadyInUseException();
        user user = new user(nextUserId(), fixedName, hashPassword(password));
        userDAO.addUser(user);
    }
    public List<user> getAllUsers(){
        return userDAO.getAllUsers();
    }
    public user getUserById(int id) throws userDoesNotExistException {
        if(!checkUserExistence(id)) throw new userDoesNotExistException();
        return userDAO.getUserById(id);
    }
    public void removeUser(user user){
        userDAO.removeUser(user.getId());
    }
    private int nextUserId(){
        nextId += 1;
        return nextId;
    }
    private String hashPassword(String password){
        return DigestUtils.sha256Hex(password);
    }
    private boolean checkUserExistence(int id){
        return userDAO.getUserById(id) != null;
    }
}
