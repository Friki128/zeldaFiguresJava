package model;

import java.util.ArrayList;
import java.util.List;

public class lineItem extends drawingItem{
    List<position> positionList = new ArrayList<>();

    public lineItem(String name, String type, String color, int width, List<position> positionList) {
        super(name, type, color, width);
        this.positionList = positionList;
    }

    public List<position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<position> positionList) {
        this.positionList = positionList;
    }
}
