package Controlers;

import Model.drawing;
import Services.drawingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewPublicDrawings")
public class viewPublicDrawingsController extends HttpServlet {
    drawingService drawingService = new drawingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<drawing> drawings = drawingService.getPublicDrawings();
        req.setAttribute("drawings", drawings);
        req.setAttribute("mode", "public");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/viewDrawings.jsp");
        requestDispatcher.forward(req, resp);
    }
}
