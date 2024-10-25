package Controlers;

import Exceptions.notOwnerException;
import Model.drawing;
import Model.user;
import Services.drawingService;
import Services.userService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/deleteUser")
public class deleteUserController extends HttpServlet {
    userService userService = new userService();
    drawingService drawingService = new drawingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/deleteUser.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        user user = (Model.user) req.getSession().getAttribute("user");
        try {
            drawingService.deleteUserDrawings(user);
            userService.removeUser(user);
            req.getSession().removeAttribute("user");
            resp.sendRedirect("/login");
        } catch (notOwnerException e) {
            req.setAttribute("error", "Couldn't delete a drawing because the user isn't owner");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
