package Controlers;

import Exceptions.drawingDoesNotExistException;
import Exceptions.notPublicException;
import Model.user;
import Services.drawingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/clone")
public class cloneController extends HttpServlet {
    drawingService drawingService = new drawingService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            drawingService.duplicate(id, user);
            resp.sendRedirect("/viewUserDrawings");
        } catch (notPublicException e) {
            errorController.redirectError("Cannot clone a drawing that isn't public", req, resp);
        } catch (drawingDoesNotExistException e) {
            errorController.redirectError("The Drawing doesn't exist", req, resp);
        }
    }
}
