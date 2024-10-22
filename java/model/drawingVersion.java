package model;

import java.util.ArrayList;
import java.util.List;

public class drawingVersion {
    String date;
    List<drawingItem> components = new ArrayList<>();

    public drawingVersion(String date, List<drawingItem> components) {
        this.date = date;
        this.components = components;
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
