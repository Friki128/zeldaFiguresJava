package Controlers;

import Exceptions.drawingDoesNotExistException;
import Exceptions.emtyNameException;
import Exceptions.notPublicException;
import Model.user;
import Services.drawingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/fuse")
public class fuseController extends HttpServlet {
    drawingService drawingService = new drawingService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        String drawingIds = req.getParameter("drawingIds");
        String name = req.getParameter("name");
        List<Integer> ids = new ArrayList<>();
        if (!drawingIds.contains(",")){
            errorController.redirectError("Cannot fuse drawings if there is less than two", req, resp);
        } else {
            for (String id : drawingIds.split(",")) {
                ids.add(Integer.parseInt(id));
            }
            try {
                drawingService.fuseDrawings(ids, name, user);
                resp.sendRedirect("/viewUserDrawings");
            } catch (notPublicException e) {
                errorController.redirectError("Cannot fuse with a drawing that isn't public.", req, resp);
            } catch (emtyNameException e) {
                errorController.redirectError("The name of the drawing cannot be empty", req, resp);
            } catch (drawingDoesNotExistException e) {
                errorController.redirectError("The Drawing doesn't exist", req, resp);
            }
        }
    }
}
