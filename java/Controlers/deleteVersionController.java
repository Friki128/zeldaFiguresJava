package Controlers;

import Exceptions.drawingDoesNotExistException;
import Exceptions.notOwnerException;
import Exceptions.versionDoesNotExistException;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int drawingId = Integer.parseInt(req.getParameter("id"));
        int versionId = Integer.parseInt(req.getParameter("versionId"));
        req.setAttribute("drawingId", drawingId);
        req.setAttribute("versionId", versionId);
        req.setAttribute("mode", "version");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/delete.jsp");
        requestDispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int drawingId = Integer.parseInt(req.getParameter("drawingId"));
        int versionId = Integer.parseInt(req.getParameter("versionId"));
        user user = (Model.user) req.getSession().getAttribute("user");
        try {
            drawingService.removeVersion(drawingId, user, versionId);
            resp.sendRedirect("/viewUserDrawings");
        } catch (notOwnerException e) {
            errorController.redirectError("Couldn't delete version because user is not the owner.", req, resp);
        } catch (versionDoesNotExistException e) {
            errorController.redirectError("The version doesn't exist", req, resp);
        } catch (drawingDoesNotExistException e) {
            errorController.redirectError("The Drawing doesn't exist", req, resp);
        }
    }
}
