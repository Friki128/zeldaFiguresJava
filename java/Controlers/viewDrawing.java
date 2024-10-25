package Controlers;

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
        try {
            drawing drawing = drawingService.getDrawingById(id, user);
            drawingVersion currentVersion = drawingService.getLatestVersion(id, user);
            drawingVersion earliestVersion = drawingService.getEarliestVersion(id, user);
            req.setAttribute("updateDate", currentVersion.getDate());
            req.setAttribute("creationDate", currentVersion.getDate());
            req.setAttribute("drawingId", drawing.getId());
            req.setAttribute("versionId", currentVersion.getId());
            req.setAttribute("name", drawing.getName());
            req.setAttribute("elements", currentVersion.getNumberOfComponents());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/viewVersion.jsp");
            requestDispatcher.forward(req, resp);
        } catch (notPublicException e) {
            errorController.redirectError("Couldn't get version because drawing isn't public.", req, resp);
        }

    }
}
