package Controlers;

import Model.user;
import Services.userService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login")
public class loginController extends HttpServlet {
    userService userService = new userService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("user") != null){
            resp.sendRedirect("/");
        }else{
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        user user = userService.login(name, password);
        if(user != null){
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/");
        }else{
            req.setAttribute("error", "Name and/or Password incorrect. Could not login.");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
