package Controlers;

import Exceptions.drawingDoesNotExistException;
import Exceptions.notPublicException;
import Exceptions.versionDoesNotExistException;
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

@WebServlet(value = "/editVersion")
public class editDrawingVersionController extends HttpServlet {
    drawingService drawingService = new drawingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        int drawingId = Integer.parseInt(req.getParameter("drawingId"));
        int versionId = Integer.parseInt(req.getParameter("versionId"));
        try {
            drawing drawing = drawingService.getDrawingById(drawingId, user);
            drawingVersion version = drawingService.getVersion(drawingId, versionId, user);
            req.setAttribute("name", drawing.getName());
            req.setAttribute("drawingId", drawing.getId());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/editDrawing.jsp");
            requestDispatcher.forward(req, resp);
        } catch (notPublicException e) {
            errorController.redirectError("Cannot edit a private drawing.", req, resp);
        } catch (versionDoesNotExistException e) {
            errorController.redirectError("The version doesn't exist", req, resp);
        } catch (drawingDoesNotExistException e) {
            errorController.redirectError("The Drawing doesn't exist", req, resp);
        }
    }
}
