package Controlers;

import Exceptions.drawingDoesNotExistException;
import Exceptions.notPublicException;
import Model.drawing;
import Model.drawingVersion;
import Model.user;
import Services.drawingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/viewDrawing")
public class viewDrawing extends HttpServlet {
    drawingService drawingService = new drawingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        String error = req.getParameter("error");
        try {
            drawing drawing = drawingService.getDrawingById(id, user);
            drawingVersion currentVersion = drawingService.getLatestVersion(id, user);
            drawingVersion earliestVersion = drawingService.getEarliestVersion(id, user);
            req.setAttribute("error", error);
            req.setAttribute("drawingId", drawing.getId());
            req.setAttribute("name", drawing.getName());
            req.setAttribute("creator", drawing.getUser().getName());
            req.setAttribute("updateDate", currentVersion.getDate());
            req.setAttribute("creationDate", earliestVersion.getDate());
            req.setAttribute("versionId", currentVersion.getId());
            req.setAttribute("elements", currentVersion.getNumberOfComponents());
            req.setAttribute("picture", currentVersion.getItems());
            req.setAttribute("isPublic", drawing.isStatus());
            req.setAttribute("isOwner", drawingService.isUserOwnerOfDrawing(user, drawing.getId()));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/viewVersion.jsp");
            requestDispatcher.forward(req, resp);
        } catch (notPublicException e) {
            errorController.redirectError("Couldn't get version because drawing isn't public.", req, resp);
        } catch (drawingDoesNotExistException e) {
            errorController.redirectError("The Drawing doesn't exist", req, resp);
        }

    }
}
