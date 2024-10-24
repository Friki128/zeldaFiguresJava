package DAO;
import Model.*;

import java.util.List;

public interface drawingDAO {
    void addDrawing(drawing drawing);
    void removeDrawing(int id);
    List<drawing> getAllDrawings();
    drawing getDrawingById(int id);
    void addVersionOffDrawing(int drawingId, drawingVersion drawingVersion);
    void removeVersionOffDrawing(int drawingId, int versionId);
    void changeDrawingStatus(int id, boolean status);
    List<drawing> getDrawingsByUserId(int id);
    List<drawing> getDrawingsByPublicStatus();
    drawingVersion getCurrentVersion(int id);
    boolean isDrawingOwner(int drawingId, int userId);
    boolean isDrawingVisible(int userId, int drawingId);
    void changeDrawingName(int drawingId, String name);
    drawingVersion getVersion(int drawingId, int versionId);
}
