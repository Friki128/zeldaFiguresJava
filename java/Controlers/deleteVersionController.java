package Controlers;

import Exceptions.notOwnerException;
import Services.drawingService;
import Model.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteVersion")
public class deleteVersionController extends HttpServlet {
    drawingService drawingService = new drawingService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int drawingId = Integer.parseInt(req.getParameter("drawingId"));
        int versionId = Integer.parseInt(req.getParameter("versionId"));
        user user = (Model.user) req.getSession().getAttribute("user");
        try {
            drawingService.removeVersion(drawingId, user, versionId);
            resp.sendRedirect("/viewDrawing?id=" + drawingId);
        } catch (notOwnerException e) {
            errorController.redirectError("Couldn't delete version because user is not the owner.", req, resp);
        }
    }
}
