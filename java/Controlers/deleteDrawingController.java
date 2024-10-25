package Controlers;

import Exceptions.notOwnerException;
import Model.user;
import Services.drawingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteDrawing")
public class deleteDrawingController extends HttpServlet {
    drawingService drawingService = new drawingService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int drawingId = Integer.parseInt(req.getParameter("id"));
        user user = (Model.user) req.getSession().getAttribute("user");
        try {
            drawingService.deleteDrawing(drawingId, user);
            resp.sendRedirect("/viewUserDrawings");
        } catch (notOwnerException e) {
            req.setAttribute("error", "Cannot delete drawing because the user isn't the owner.");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/errorPage.jsp");
        }
    }
}
