package Controlers;

import Exceptions.drawingDoesNotExistException;
import Exceptions.notOwnerException;
import Model.user;
import Services.drawingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addVersion")
public class addVersionController extends HttpServlet {
    drawingService drawingService = new drawingService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        String picture = req.getParameter("picture");
        try {
            drawingService.addVersion(id, user, picture);
            resp.sendRedirect("/viewUserDrawings");
        } catch (notOwnerException e) {
            errorController.redirectError("Can't add a new version to a drawing you don't own.", req, resp);
        } catch (drawingDoesNotExistException e) {
            errorController.redirectError("The Drawing doesn't exist", req, resp);
        }
    }
}
