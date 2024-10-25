package Services;

import DAO.userDAOImpl;
import Exceptions.*;
import Model.user;

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
    int nextId = 0;
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
        user user = new user(nextUserId(), fixedName, password);
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
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Arrays.toString(hash);
        } catch (NoSuchAlgorithmException e) {
            return password;
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean checkUserExistence(int id){
        return userDAO.getUserById(id) != null;
    }
}
