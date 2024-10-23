package DAO;
import model.*;

import java.util.List;

public interface drawingDAO {
    public void addDrawing(drawing drawing);
    public void removeDrawing(int id);
    public List<drawing> getAllDrawings();
    public drawing getDrawingById(int id);
    public void addVersionOffDrawing(int drawingId, drawingVersion drawingVersion);
    public void removeVersionOffDrawing(int drawingId, int versionId);
    public void changeDrawingStatus(int id, boolean status);
    public List<drawing> getDrawingsByUserId(int id);
    public List<drawing> getDrawingsByPublicStatus();
}
