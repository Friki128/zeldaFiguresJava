package View;

public class drawingVersionView {
    String picture;
    String name;
    int drawingId;

    public drawingVersionView(String picture, String name, int drawingId) {
        this.picture = picture;
        this.name = name;
        this.drawingId = drawingId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawingId() {
        return drawingId;
    }

    public void setDrawingId(int drawingId) {
        this.drawingId = drawingId;
    }
}
