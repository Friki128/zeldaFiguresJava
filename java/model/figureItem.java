package model;

public class figureItem extends drawingItem{
    position position;
    int height;

    public figureItem(int id, String name, String type, String color, int width, model.position position, int height) {
        super(id, name, type, color, width);
        this.position = position;
        this.height = height;
    }

    public model.position getPosition() {
        return position;
    }

    public void setPosition(model.position position) {
        this.position = position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
