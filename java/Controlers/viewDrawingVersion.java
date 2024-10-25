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

@WebServlet(value = "/viewDrawingVersion")
public class viewDrawingVersion extends HttpServlet {
    drawingService drawingService = new drawingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        int drawingId = Integer.parseInt(req.getParameter("drawingId"));
        int versionId = Integer.parseInt(req.getParameter("versionId"));
        try {
            drawingVersion version = drawingService.getVersion(drawingId, versionId, user);
            drawing drawing = drawingService.getDrawingById(drawingId, user);
            drawingVersion original = drawingService.getEarliestVersion(drawingId, user);
            req.setAttribute("name", drawing.getName());
            req.setAttribute("drawingId", drawing.getId());
            req.setAttribute("versionId", version.getId());
            req.setAttribute("updateDate", version.getDate());
            req.setAttribute("creationDate", original.getDate());
            req.setAttribute("elements", version.getNumberOfComponents());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/viewVersion.jsp");
            requestDispatcher.forward(req, resp);
        } catch (notPublicException e) {
            errorController.redirectError("Can't access this version of the drawing since it isn't public.", req, resp);
        }
    }
}
