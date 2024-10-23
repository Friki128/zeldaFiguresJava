package Services;

import DAO.drawingDAOImpl;
import Exceptions.notOwnerException;
import Model.drawing;
import Model.drawingVersion;
import Model.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class drawingService {
    drawingDAOImpl drawingDAO = new drawingDAOImpl();
    int drawingId = 0;
    int versionId = 0;
    public void addDrawing(String name, user user){
        drawingVersion version = new drawingVersion(nextVersionId(), getDate(), new ArrayList<>());
        List<drawingVersion> versions = new ArrayList<>();
        versions.add(version);
        drawing drawing = new drawing(nextDrawingId(),name,false, user, versions);
        drawingDAO.addDrawing(drawing);
    }
    public void deleteDrawing(int drawingId, int userId) throws notOwnerException {
        drawingDAO.removeDrawing(drawingId);
    }
    public List<drawing> getAllDrawings(){
        return drawingDAO.getAllDrawings();
    }
    public List<drawing> getPublicDrawings(){
        return drawingDAO.getDrawingsByPublicStatus();
    }
    public drawing getDrawingById(int drawingId, int userId) throws notOwnerException {
        drawing drawing = drawingDAO.getDrawingById(drawingId);
        if(!drawing.isStatus()) throw new notOwnerException();
        return drawing;
    }

    public List<drawing> getUserDrawings(int userId){
        return drawingDAO.getDrawingsByUserId(userId);
    }
    public void addVersion(int drawingId){
        drawingVersion version = new drawingVersion(nextVersionId(), getDate(), new ArrayList<>());
        drawingDAO.addVersionOffDrawing(drawingId, version);
    }

    public void removeVersion(int drawingId, int versionId){
        drawingDAO.removeVersionOffDrawing(drawingId, versionId);
    }

    public boolean isUserOwnerOfDrawing(int userId, int drawingId){
        return drawingDAO.isDrawingOwner(drawingId, userId);
    }
    private int nextDrawingId(){
        return drawingId;
    }
    private int nextVersionId(){
        return versionId;
    }
    private String getDate(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentDate);
    }
}
