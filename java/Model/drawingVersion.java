package Model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class drawingVersion {

    int id;
    String date;
    String items = "";

    public drawingVersion(int id, String date, String items) {
        this.id = id;
        this.date = date;
        this.items = items;
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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
    public int getNumberOfComponents(){
        JSONObject object = new JSONObject(items);
        return object.length();
    }
}
