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
        String name  = req.getParameter("name");
        try {
            drawingService.addDrawing(name, user);
            resp.sendRedirect("/viewUserDrawings");
        } catch (emtyNameException e) {
            errorController.redirectErrorToPage("The name cannot be empty.", req, resp, "viewUserDrawings?");
        }
    }
}
