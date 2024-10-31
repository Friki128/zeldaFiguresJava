package Services;

import DAO.drawingDAOImpl;
import Exceptions.*;
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
    static int drawingId = 0;
    static int versionId = 0;
    public void addDrawing(String name, user user) throws emtyNameException {
        String fixedName = name.trim();
        if(fixedName.isEmpty()) throw new emtyNameException();
        drawingVersion version = new drawingVersion(nextVersionId(), getDate(), "{}");
        drawing drawing = new drawing(nextDrawingId(),fixedName,false, user, new ArrayList<>());
        drawing.addVersion(version);
        drawingDAO.addDrawing(drawing);
    }
    public void deleteDrawing(int drawingId, user user) throws notOwnerException, drawingDoesNotExistException {
        if(!isUserOwnerOfDrawing(user, drawingId)) throw new notOwnerException();
        if(!checkDrawingExistence(drawingId)) throw new drawingDoesNotExistException();
        drawingDAO.removeDrawing(drawingId);
    }
    public void deleteUserDrawings(user user) throws notOwnerException, drawingDoesNotExistException {
        List<drawing> userDrawings = getUserDrawings(user);
        for(drawing drawing : userDrawings) deleteDrawing(drawing.getId(), user);
    }
    public List<drawing> getAllDrawings(){
        return drawingDAO.getAllDrawings();
    }
    public List<drawing> getPublicDrawings(){
        return drawingDAO.getDrawingsByPublicStatus();
    }
    public drawing getDrawingById(int drawingId, user user) throws notPublicException, drawingDoesNotExistException {
        if(!isDrawingVisible(user, drawingId)) throw new notPublicException();
        if(!checkDrawingExistence(drawingId)) throw new drawingDoesNotExistException();
        return drawingDAO.getDrawingById(drawingId);
    }

    public List<drawing> getUserDrawings(user user){
        return drawingDAO.getDrawingsByUserId(user.getId());
    }
    public drawingVersion getLatestVersion(int drawingId, user user) throws notPublicException, drawingDoesNotExistException {
        if(!isDrawingVisible(user, drawingId)) throw new notPublicException();
        return drawingDAO.getCurrentVersion(drawingId);
    }

    public List<drawingVersion> getVersions(int drawingId, user user) throws notPublicException, drawingDoesNotExistException {
        drawing drawing = getDrawingById(drawingId, user);
        return drawing.getVersions();
    }

    public drawingVersion getVersion(int drawingId, int versionId, user user) throws notPublicException, versionDoesNotExistException, drawingDoesNotExistException {
        if(!isDrawingVisible(user, drawingId)) throw new notPublicException();
        if(!checkVersionExistence(drawingId, versionId)) throw new versionDoesNotExistException();
        return drawingDAO.getVersion(drawingId, versionId);
    }
    public void addVersion(int drawingId, user user,String items) throws notOwnerException, drawingDoesNotExistException, versionDoesNotExistException, notPublicException {
        if(!isUserOwnerOfDrawing(user, drawingId)) throw new notOwnerException();
        drawingVersion prevVersion = getLatestVersion(drawingId, user);
        if(prevVersion.getNumberOfComponents() == 0) drawingDAO.removeVersionOffDrawing(drawingId, prevVersion.getId());
        drawingVersion version = new drawingVersion(nextVersionId(), getDate(), items);
        drawingDAO.addVersionOffDrawing(drawingId, version);
    }
    public void removeVersion(int drawingId, user user ,int versionId) throws notOwnerException, versionDoesNotExistException, drawingDoesNotExistException {
        if(!isUserOwnerOfDrawing(user, drawingId)) throw new notOwnerException();
        if(!checkVersionExistence(drawingId, versionId)) throw new versionDoesNotExistException();
        drawingDAO.removeVersionOffDrawing(drawingId, versionId);
        drawing drawing = drawingDAO.getDrawingById(drawingId);
        if(drawing.getVersions().isEmpty()) deleteDrawing(drawingId, user);
    }
    public void fuseDrawings(List<Integer> drawings, String name, user user) throws notPublicException, emtyNameException, drawingDoesNotExistException {
        String fixedName = name.trim();
        if(fixedName.isEmpty()) throw new emtyNameException();
        drawing drawing = new drawing(nextDrawingId(), fixedName, false, user, new ArrayList<>());
        drawingVersion version = new drawingVersion(nextVersionId(), getDate(), "{}");
        JSONObject object = new JSONObject();
        int iterant = 0;
        for (Integer id : drawings){
               if (!isDrawingVisible(user, id)) throw new notPublicException();
               if(!checkDrawingExistence(id)) throw new drawingDoesNotExistException();
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
    public void duplicate(int drawingId, user user) throws notPublicException, drawingDoesNotExistException {
        drawing drawing = getDrawingById(drawingId, user);
        drawing newDrawing = new drawing(nextDrawingId(), drawing.getName() + "_copy", false, user, new ArrayList<>());
        newDrawing.addVersion(drawingDAO.getCurrentVersion(drawingId));
        drawingDAO.addDrawing(newDrawing);
    }
    public boolean isUserOwnerOfDrawing(user user, int drawingId) throws drawingDoesNotExistException {
        if(!checkDrawingExistence(drawingId)) throw new drawingDoesNotExistException();
        return drawingDAO.isDrawingOwner(drawingId, user.getId());
    }
    public void changeDrawingPublicStatus(int drawingId, user user,boolean status) throws notOwnerException, drawingDoesNotExistException {
        if(!isUserOwnerOfDrawing(user, drawingId)) throw new notOwnerException();
        drawingDAO.changeDrawingStatus(drawingId, status);
    }
    public void changeDrawingName(int drawingId, user user,String name) throws notOwnerException, emtyNameException, drawingDoesNotExistException {
        if(!isUserOwnerOfDrawing(user, drawingId)) throw new notOwnerException();
        String fixedName = name.trim();
        if(fixedName.isEmpty()) throw new emtyNameException();
        drawingDAO.changeDrawingName(drawingId, fixedName);
    }
    public boolean isDrawingVisible(user user, int drawingId) throws drawingDoesNotExistException {
        if(!checkDrawingExistence(drawingId)) throw new drawingDoesNotExistException();
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

    public drawingVersion getEarliestVersion(int drawingId, user user) throws notPublicException, drawingDoesNotExistException {
        if(!isDrawingVisible(user, drawingId)) throw new notPublicException();
        return drawingDAO.getEarliestVersion(drawingId, user.getId());
    }
}
