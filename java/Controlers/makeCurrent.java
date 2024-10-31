package Controlers;

import Exceptions.drawingDoesNotExistException;
import Exceptions.notPublicException;
import Exceptions.versionDoesNotExistException;
import Model.user;
import Services.drawingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/makeCurrent")
public class makeCurrent extends HttpServlet {
    drawingService drawingService = new drawingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        int drawingId = Integer.parseInt(req.getParameter("drawingId"));
        int versionId = Integer.parseInt(req.getParameter("versionId"));
        try {
            drawingService.makeCurrentVersion(drawingId, versionId, user);
            resp.sendRedirect("/viewDrawing?id=" + drawingId);
        } catch (versionDoesNotExistException e) {
            errorController.redirectError("Version does not exist", req, resp);
        } catch (drawingDoesNotExistException e) {
            errorController.redirectError("Drawing does not exist", req, resp);
        } catch (notPublicException e) {
            errorController.redirectError("The drawing isn't public", req, resp);
        }
    }
}
