package model;

public class drawingItem {
    String name;
    String type;
    String color;
    int width;

    public drawingItem(String name, String type, String color, int width) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.width = width;
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
