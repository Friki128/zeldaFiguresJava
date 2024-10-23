package DAO;

import model.drawing;
import model.drawingVersion;

import java.util.ArrayList;
import java.util.List;

public class drawingDAOImpl implements drawingDAO{
    List<drawing> drawings = new ArrayList<>();
    @Override
    public void addDrawing(drawing drawing) {
        drawings.add(drawing);
    }

    @Override
    public void removeDrawing(int id) {
        drawing drawing = getDrawingById(id);
        drawings.remove(drawing);
    }

    @Override
    public List<drawing> getAllDrawings() {
        return drawings;
    }

    @Override
    public drawing getDrawingById(int id) {
        for(drawing drawing : drawings){
            if (drawing.getId() == id) return drawing;
        }
        return null;
    }

    @Override
    public void addVersionOffDrawing(int drawingId, drawingVersion drawingVersion) {
        drawing drawing = getDrawingById(drawingId);
        drawing.addVersion(drawingVersion);
    }

    @Override
    public void removeVersionOffDrawing(int drawingId, int versionId) {
        drawing drawing = getDrawingById(drawingId);
        for (drawingVersion version : drawing.getVersions()){
            if (version.getId() == versionId) drawing.addVersion(version);
        }
    }

    @Override
    public void changeDrawingStatus(int id, boolean status) {
        drawing drawing = getDrawingById(id);
        drawing.setStatus(status);
    }

    @Override
    public List<drawing> getDrawingsByUserId(int id) {
        List<drawing> result = new ArrayList<>();
        for (drawing drawing : drawings){
            if (drawing.getUser().getId() == id) result.add(drawing);
        }
        return result;
    }

    @Override
    public List<drawing> getDrawingsByPublicStatus() {
       List<drawing> result = new ArrayList<>();
       for(drawing drawing : drawings){
           if (drawing.isStatus()) result.add(drawing);
       }
       return result;
    }
}
