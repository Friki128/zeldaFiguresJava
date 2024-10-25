package Controlers;

import Exceptions.emtyNameException;
import Model.user;
import Services.drawingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addDrawing")
public class addDrawingController extends HttpServlet {
    drawingService drawingService = new drawingService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        try {
            drawingService.addDrawing("New Drawing", user);
            resp.sendRedirect("/viewUserDrawings");
        } catch (emtyNameException e) {
            req.setAttribute("error", "The name cannot be empty.");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
