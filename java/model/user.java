package model;

import java.util.ArrayList;
import java.util.List;

public class user {
    int id;
    String name;
    String password;

    public user(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
