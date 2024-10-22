package model;

import java.util.ArrayList;
import java.util.List;

public class user {
    String name;
    String password;
    List<drawing> drawings = new ArrayList<>();

    public user(String name, String password, List<drawing> drawings) {
        this.name = name;
        this.password = password;
        this.drawings = drawings;
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

    public List<drawing> getDrawings() {
        return drawings;
    }

    public void setDrawings(List<drawing> drawings) {
        this.drawings = drawings;
    }
}
