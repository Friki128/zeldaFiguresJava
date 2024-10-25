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

@WebServlet(value = "/editDrawing")
public class editDrawing extends HttpServlet {
    drawingService drawingService = new drawingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        int drawingId = Integer.parseInt(req.getParameter("drawingId"));
        try {
            drawing drawing = drawingService.getDrawingById(drawingId, user);
            drawingVersion version = drawingService.getLatestVersion(drawingId, user);
            req.setAttribute("name", drawing.getName());
            req.setAttribute("drawingId", drawing.getId());
            req.setAttribute("picture", version.getItems());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/editDrawing.jsp");
            requestDispatcher.forward(req, resp);
        } catch (notPublicException e) {
            errorController.redirectError("Cannot edit a private drawing.", req, resp);
        }
    }
}
