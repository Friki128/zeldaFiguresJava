package model;

import java.util.ArrayList;
import java.util.List;

public class drawingVersion {

    int id;
    String date;
    List<drawingItem> components = new ArrayList<>();

    public drawingVersion(int id, String date, List<drawingItem> components) {
        this.id = id;
        this.date = date;
        this.components = components;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<drawingItem> getComponents() {
        return components;
    }

    public void setComponents(List<drawingItem> components) {
        this.components = components;
    }
}
