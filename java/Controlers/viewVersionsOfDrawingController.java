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
import java.util.List;

@WebServlet(value = "/viewVersionsOfDrawing")
public class viewVersionsOfDrawingController extends HttpServlet {
    drawingService drawingService = new drawingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        int drawingId = Integer.parseInt(req.getParameter("id"));
        try {
            List<drawingVersion> versions = drawingService.getVersions(drawingId, user);
            drawing drawing = drawingService.getDrawingById(drawingId, user);
            req.setAttribute("versions", versions);
            req.setAttribute("name", drawing.getName());
            req.setAttribute("drawingId", drawing.getId());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/viewVersions.jsp");
            requestDispatcher.forward(req, resp);
        } catch (notPublicException e) {
            errorController.redirectError("Couldn't access versions of the drawing since the drawing isn't Public", req, resp);
        } catch (drawingDoesNotExistException e) {
            errorController.redirectError("The drawing doesn't exist.", req, resp);
        }
    }
}
