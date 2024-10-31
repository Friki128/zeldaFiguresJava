package Controlers;

import Exceptions.drawingDoesNotExistException;
import Exceptions.notPublicException;
import Model.drawing;
import Model.user;
import Services.drawingService;
import View.*;

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
        String error = req.getParameter("error");
        List<drawing> drawings = drawingService.getUserDrawings(user);
        try {
            List<drawingVersionView> drawingVersions = drawingVersionCombiner.combine(drawings, user);
            req.setAttribute("drawings", drawingVersions);
            req.setAttribute("mode", "user");
            req.setAttribute("error", error);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/viewDrawings.jsp");
            requestDispatcher.forward(req, resp);
        } catch (drawingDoesNotExistException e) {
            errorController.redirectError("Drawing does not exist", req, resp);
        } catch (notPublicException e) {
            errorController.redirectError("Drawing is not public", req, resp);
        }
    }
}
