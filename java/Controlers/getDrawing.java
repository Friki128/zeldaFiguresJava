package Controlers;

import Exceptions.drawingDoesNotExistException;
import Exceptions.notPublicException;
import Model.user;
import Services.drawingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/getDrawing")
public class getDrawing extends HttpServlet {
    drawingService drawingService = new drawingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
            resp.getWriter().write(drawingService.getLatestVersion(id, user).getItems());
        } catch (notPublicException e) {
            errorController.redirectError("The image isn't public.", req, resp);
        } catch (drawingDoesNotExistException e) {
            errorController.redirectError("The image doesn't exist.", req, resp);
        }
    }
}
