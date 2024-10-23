package Model;

public class starItem extends figureItem{
    int points;

    public starItem(int id, String name, String type, String color, int width, Model.position position, int height, boolean filled, int points) {
        super(id, name, type, color, width, position, height, filled);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
