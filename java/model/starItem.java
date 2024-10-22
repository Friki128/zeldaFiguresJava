package model;

public class starItem extends figureItem{
    int points;

    public starItem(String name, String type, String color, int width, model.position position, int height, int points) {
        super(name, type, color, width, position, height);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
