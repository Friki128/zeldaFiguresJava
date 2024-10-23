package Model;

import java.util.ArrayList;
import java.util.List;

public class drawing {
    int id;
    String name;
    boolean status;
    user user;
    List<drawingVersion> versions = new ArrayList<>();

    public drawing(int id, String name, boolean status, user user,List<drawingVersion> versions) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.user = user;
        this.versions = versions;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public user getUser(){
        return user;
    }

    public void setUser(user user){
        this.user = user;
    }

    public List<drawingVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<drawingVersion> versions) {
        this.versions = versions;
    }
    public void addVersion(drawingVersion drawingVersion){
        versions.add(drawingVersion);
    }
    public void removeVersion(drawingVersion drawingVersion){
        versions.remove(drawingVersion);
    }
}
