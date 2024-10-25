package Controlers;

import Model.drawing;
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

@WebServlet(value = "/viewUserDrawings")
public class viewUserDrawingsController extends HttpServlet {
    drawingService drawingService = new drawingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        List<drawing> drawings = drawingService.getUserDrawings(user);
        req.setAttribute("drawings", drawings);
        req.setAttribute("mode", "user");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/viewDrawings.jsp");
        requestDispatcher.forward(req, resp);
    }
}
