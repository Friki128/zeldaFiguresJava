package Services;

import DAO.drawingDAOImpl;
import Exceptions.notPublicException;
import Model.drawing;
import Model.drawingItem;
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
    public void deleteDrawing(int drawingId){
        drawingDAO.removeDrawing(drawingId);
    }
    public List<drawing> getAllDrawings(){
        return drawingDAO.getAllDrawings();
    }
    public List<drawing> getPublicDrawings(){
        return drawingDAO.getDrawingsByPublicStatus();
    }
    public drawing getDrawingById(int drawingId, int userId) throws notPublicException {
        drawing drawing = drawingDAO.getDrawingById(drawingId);
        if(!drawing.isStatus() && !isUserOwnerOfDrawing(drawingId, userId)) throw new notPublicException();
        return drawing;
    }

    public List<drawing> getUserDrawings(int userId){
        return drawingDAO.getDrawingsByUserId(userId);
    }
    public drawingVersion getLatestVersion(int id){
        return drawingDAO.getCurrentVersion(id);
    }

    public drawingVersion getVersion(int drawingId, int versionId){
        return drawingDAO.getVersion(drawingId, versionId);
    }
    public void addVersion(int drawingId, String itemList){
        drawingVersion version = new drawingVersion(nextVersionId(), getDate(), new ArrayList<>());
        drawingDAO.addVersionOffDrawing(drawingId, version);
    }
    public void removeVersion(int drawingId, int versionId){
        drawingDAO.removeVersionOffDrawing(drawingId, versionId);
    }
    public void fuseDrawings(List<Integer> drawings, String name, user user) throws notPublicException {
        drawing drawing = new drawing(nextDrawingId(), name, false, user, new ArrayList<>());
        drawingVersion version = new drawingVersion(nextVersionId(), getDate(), new ArrayList<>());
        for (Integer id : drawings){
               if (!isDrawingVisible(user.getId(), id)) throw new notPublicException();
               drawingVersion nextVersion = drawingDAO.getCurrentVersion(id);
               for(drawingItem item : nextVersion.getComponents()){
                   version.addComponent(item);
               }
        }
        drawing.addVersion(version);
        drawingDAO.addDrawing(drawing);
    }
    public boolean isUserOwnerOfDrawing(int userId, int drawingId){
        return drawingDAO.isDrawingOwner(drawingId, userId);
    }
    public void changeDrawingPublicStatus(int drawingId, boolean status){
        drawingDAO.changeDrawingStatus(drawingId, status);
    }
    public void changeDrawingName(int drawingId, String name){
        drawingDAO.changeDrawingName(drawingId, name);
    }
    public boolean isDrawingVisible(int userId, int drawingId){
        return drawingDAO.isDrawingVisible(userId, drawingId);
    }
    private int nextDrawingId(){
        drawingId += 1;
        return drawingId;
    }
    private int nextVersionId(){
        versionId += 1;
        return versionId;
    }
    private String getDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(currentDate);
    }
    private boolean checkDrawingExistence(int id){
        return drawingDAO.getDrawingById(id) != null;
    }
    private boolean checkVersionExistence(int drawingId, int versionId){
        return drawingDAO.getVersion(drawingId, versionId) != null;
    }
}
