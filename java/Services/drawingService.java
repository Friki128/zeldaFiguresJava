package Services;

import DAO.drawingDAOImpl;
import Exceptions.notOwnerException;
import Exceptions.notPublicException;
import Model.drawing;
import Model.drawingVersion;
import Model.user;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class drawingService {
    drawingDAOImpl drawingDAO = new drawingDAOImpl();
    int drawingId = 0;
    int versionId = 0;
    public void addDrawing(String name, user user){
        drawingVersion version = new drawingVersion(nextVersionId(), getDate(), "{}");
        List<drawingVersion> versions = new ArrayList<>();
        versions.add(version);
        drawing drawing = new drawing(nextDrawingId(),name,false, user, versions);
        drawingDAO.addDrawing(drawing);
    }
    public void deleteDrawing(int drawingId, user user) throws notOwnerException {
        if(!isUserOwnerOfDrawing(user, drawingId)) throw new notOwnerException();
        drawingDAO.removeDrawing(drawingId);
    }
    public void deleteUserDrawings(user user) throws notOwnerException {
        List<drawing> userDrawings = getUserDrawings(user);
        for(drawing drawing : userDrawings) deleteDrawing(drawing.getId(), user);
    }
    public List<drawing> getAllDrawings(){
        return drawingDAO.getAllDrawings();
    }
    public List<drawing> getPublicDrawings(){
        return drawingDAO.getDrawingsByPublicStatus();
    }
    public drawing getDrawingById(int drawingId, user user) throws notPublicException {
        if(!isDrawingVisible(user, drawingId)) throw new notPublicException();
        return drawingDAO.getDrawingById(drawingId);
    }

    public List<drawing> getUserDrawings(user user){
        return drawingDAO.getDrawingsByUserId(user.getId());
    }
    public drawingVersion getLatestVersion(int drawingId, user user) throws notPublicException {
        if(!isDrawingVisible(user, drawingId)) throw new notPublicException();
        return drawingDAO.getCurrentVersion(drawingId);
    }

    public List<drawingVersion> getVersions(int drawingId, user user) throws notPublicException {
        drawing drawing = getDrawingById(drawingId, user);
        return drawing.getVersions();
    }

    public drawingVersion getVersion(int drawingId, int versionId, user user) throws notPublicException {
        if(!isDrawingVisible(user, drawingId)) throw new notPublicException();
        return drawingDAO.getVersion(drawingId, versionId);
    }
    public void addVersion(int drawingId, user user,String items) throws notOwnerException {
        if(!isUserOwnerOfDrawing(user, drawingId)) throw new notOwnerException();
        drawingVersion version = new drawingVersion(nextVersionId(), getDate(), items);
        drawingDAO.addVersionOffDrawing(drawingId, version);
    }
    public void removeVersion(int drawingId, user user ,int versionId) throws notOwnerException {
        if(!isUserOwnerOfDrawing(user, drawingId)) throw new notOwnerException();
        drawingDAO.removeVersionOffDrawing(drawingId, versionId);
    }
    public void fuseDrawings(List<Integer> drawings, String name, user user) throws notPublicException {
        drawing drawing = new drawing(nextDrawingId(), name, false, user, new ArrayList<>());
        drawingVersion version = new drawingVersion(nextVersionId(), getDate(), "{}");
        JSONObject object = new JSONObject();
        int iterant = 0;
        for (Integer id : drawings){
               if (!isDrawingVisible(user, id)) throw new notPublicException();
               drawingVersion nextVersion = drawingDAO.getCurrentVersion(id);
               JSONObject nextJSON = new JSONObject(nextVersion.getItems());
               for(String key : JSONObject.getNames(nextJSON))
                {
                    object.put(iterant + key, nextJSON.get(key));
                }
               iterant++;

        }
        version.setItems(object.toString());
        drawing.addVersion(version);
        drawingDAO.addDrawing(drawing);
    }
    public void duplicate(int drawingId, user user) throws notPublicException {
        drawing drawing = getDrawingById(drawingId, user);
        drawing newDrawing = new drawing(nextDrawingId(), drawing.getName() + "_copy", false, user, new ArrayList<>());
        newDrawing.addVersion(drawingDAO.getCurrentVersion(drawingId));
        drawingDAO.addDrawing(newDrawing);
    }
    public boolean isUserOwnerOfDrawing(user user, int drawingId){
        return drawingDAO.isDrawingOwner(drawingId, user.getId());
    }
    public void changeDrawingPublicStatus(int drawingId, user user,boolean status) throws notOwnerException {
        if(!isUserOwnerOfDrawing(user, drawingId)) throw new notOwnerException();
        drawingDAO.changeDrawingStatus(drawingId, status);
    }
    public void changeDrawingName(int drawingId, user user,String name) throws notOwnerException {
        if(!isUserOwnerOfDrawing(user, drawingId)) throw new notOwnerException();
        drawingDAO.changeDrawingName(drawingId, name);
    }
    public boolean isDrawingVisible(user user, int drawingId){
        return drawingDAO.isDrawingVisible(user.getId(), drawingId);
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
