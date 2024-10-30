package View;

import Exceptions.drawingDoesNotExistException;
import Exceptions.notPublicException;
import Model.drawing;
import Model.user;
import Services.drawingService;

import java.util.ArrayList;
import java.util.List;

public class drawingVersionCombiner {
    static drawingService drawingService = new drawingService();
    static public List<drawingVersionView> combine(List<drawing> drawings, user user) throws drawingDoesNotExistException, notPublicException {
        List<drawingVersionView> result = new ArrayList<>();
        for(drawing drawing : drawings){
            result.add(new drawingVersionView(drawingService.getLatestVersion(drawing.getId(), user).getItems(), drawing.getName(), drawing.getId()));
        }
        return result;
    }
}
