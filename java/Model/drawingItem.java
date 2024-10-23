package Model;

public class drawingItem {
    int id;
    String name;
    String type;
    String color;
    int width;

    public drawingItem(int id, String name, String type, String color, int width) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.color = color;
        this.width = width;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
