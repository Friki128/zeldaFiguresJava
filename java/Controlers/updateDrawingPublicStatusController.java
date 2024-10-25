package Controlers;

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

@WebServlet(value = "/updateDrawingPublicStatus")
public class updateDrawingPublicStatusController extends HttpServlet {
    drawingService drawingService = new drawingService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        boolean status = Boolean.parseBoolean(req.getParameter("status"));
        try {
            drawingService.changeDrawingPublicStatus(id, user, status);
            resp.sendRedirect("/viewDrawing?id=" + id);
        } catch (notOwnerException e) {
            errorController.redirectError("Cannot change the status of a drawing you aren't the owner of.", req, resp);
        }
    }
}
