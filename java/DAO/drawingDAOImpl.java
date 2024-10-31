package DAO;

import Model.drawing;
import Model.drawingVersion;

import java.util.ArrayList;
import java.util.List;

public class drawingDAOImpl implements drawingDAO{
    static List<drawing> drawings = new ArrayList<>();
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
        drawingVersion removeVersion = getVersion(drawingId, versionId);
        drawing.removeVersion(removeVersion);
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

    @Override
    public drawingVersion getCurrentVersion(int id) {
        drawing drawing = getDrawingById(id);
        if(drawing.getVersions().isEmpty()) return null;
        drawingVersion result = drawing.getVersions().get(0);
        for (drawingVersion version : drawing.getVersions()){
            if (version.getId() > result.getId()) result = version;
        }
        return result;
    }

    @Override
    public boolean isDrawingOwner(int drawingId, int userId) {
        drawing drawing = getDrawingById(drawingId);
        return drawing.getUser().getId() == userId;
    }

    @Override
    public boolean isDrawingVisible(int userId, int drawingId) {
        return isDrawingOwner(drawingId, userId) || getDrawingById(drawingId).isStatus();
    }

    @Override
    public void changeDrawingName(int drawingId, String name) {
        drawing drawing = getDrawingById(drawingId);
        drawing.setName(name);
    }

    @Override
    public drawingVersion getVersion(int drawingId, int versionId) {
        drawing drawing = getDrawingById(drawingId);
        for(drawingVersion version : drawing.getVersions()){
            if (version.getId() == versionId) return version;
        }
        return null;
    }

    @Override
    public drawingVersion getEarliestVersion(int drawingId, int id) {
        drawing drawing = getDrawingById(drawingId);
        if(drawing.getVersions().isEmpty()) return null;
        drawingVersion result = drawing.getVersions().get(0);
        for (drawingVersion version : drawing.getVersions()){
            if (version.getId() < result.getId()) result = version;
        }
        return result;
    }
}
