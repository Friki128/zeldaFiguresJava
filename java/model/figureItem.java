package model;

public class figureItem extends drawingItem{
    position position;
    int height;

    boolean filled;

    public figureItem(int id, String name, String type, String color, int width, model.position position, int height, boolean filled) {
        super(id, name, type, color, width);
        this.position = position;
        this.height = height;
        this.filled = filled;
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

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
