package Controlers;

import Exceptions.emtyNameException;
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

@WebServlet(value = "/updateDrawingName")
public class updateDrawingNameController extends HttpServlet {
    drawingService drawingService = new drawingService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("drawingId"));
        String name = req.getParameter("name");
        user user = (Model.user) req.getSession().getAttribute("user");
        try {
            drawingService.changeDrawingName(id, user, name);
            resp.sendRedirect("/viewDrawing?id=" + id);
        } catch (notOwnerException e) {
            errorController.redirectError("Can't change drawing name without being the owner.", req, resp);
        } catch (emtyNameException e) {
            errorController.redirectError("The name can't be empty", req, resp);
        }

    }

}
