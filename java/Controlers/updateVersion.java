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

@WebServlet(value="/updateVersion")
public class updateVersion extends HttpServlet {
    drawingService drawingService = new drawingService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user user = (Model.user) req.getSession().getAttribute("user");
        System.out.println(req.getParameter("id"));
        int id = Integer.parseInt(req.getParameter("id"));
        String picture = req.getParameter("picture");
        try {
            System.out.println("hola");
            drawingService.updateVersion(id, picture, user);
        } catch (drawingDoesNotExistException e) {
            System.out.println("adios");
            errorController.redirectError("The image doesn't exist.", req, resp);
        } catch (notPublicException e) {
            errorController.redirectError("The image isn't public.", req, resp);
        }
    }
}
